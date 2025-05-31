package com.example.exp3.thread2;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对优惠券秒杀的线程
 */
public class SalesCallable implements Callable<Boolean> {
    // 优惠券数量，在多线程中共享，需要线程安全控制
    private static AtomicInteger couponCount;

    // 顾客信息
    private final String customerName;

    // 初始化优惠券数量
    public static void initCoupons(int total){
        couponCount = new AtomicInteger(total);
    }

    // 构造方法中传入顾客信息
    public SalesCallable(String customerName) {
        this.customerName = customerName;
    }
    
    // 优惠券抢购处理：采用线程安全的方式处理剩余优惠券
    @Override
    public Boolean call() throws Exception {
        int current; // 当前线程获取的优惠券数量
        int newValue; // 更新后的优惠券数量
        do{
            // 通过原子类定义方式，线程安全的获取当前的优惠券数量
            current = couponCount.get();
            // 判断当前优惠券是否没有了
            if(current <= 0){
                System.out.println(customerName + "抢购失败，优惠券已售罄！");
                //Callable的call方法可以返回值
                return false;
            }
            // 抢购成功，更新内部变量
            newValue = current - 1;
            // 比较以后再更新原子类的值
        }while(!couponCount.compareAndSet(current, newValue));
        //线程安全的获取当前优惠券数量
        int remainCoupons = couponCount.get();
        System.out.println(customerName + "抢购成功！剩余优惠券数量：" + remainCoupons);

        // 返回优惠券是否还有剩余
        return remainCoupons > 0;
    
    }

}

