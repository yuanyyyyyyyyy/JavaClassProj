package com.example.exp3.thread1;

/**
 * 定义线程类，实现秒杀逻辑
 * 用 Callable<String> 来返回每个顾客抢券的结果，同时线程共享同一个优惠券库存对象。
 */
import java.util.concurrent.Callable;

public class CouponTask implements Callable<String> {
    private Customer customer;//顾客
    private Coupon coupon;//优惠券

    public CouponTask(Customer customer, Coupon coupon) {
        this.customer = customer;
        this.coupon = coupon;
    }

    @Override
    public String call(){
        synchronized(coupon){
            if(coupon.getCount() > 0){
                int remaining = coupon.decrement();//抢券成功
                return customer.getName() + "抢到优惠券！剩余：" + remaining;
            }else{
                return "优惠券已抢完！" + customer.getName() + "抢购失败";
            }
        }
    }

   
}
