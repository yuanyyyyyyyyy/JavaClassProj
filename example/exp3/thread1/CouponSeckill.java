package com.example.exp3.thread1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用线程池 + Callable + FutureTask 模拟多个顾客同时抢购优惠券
 */
public class CouponSeckill {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //初始化优惠券10张
        Coupon coupon = new Coupon(10);

        /**
         * 创建线程池
         * 线程池就像是雇了 5 个收银员（同时最多处理5个顾客）。
         * 避免每来一个顾客都临时招一个人（太浪费资源），而是雇一批员工干活。
         */
        ExecutorService pool = Executors.newFixedThreadPool(5);

        //存储抢券结果
        List<Future<String>> results = new ArrayList<>();

        int customerId = 1;
        /**
         * 新来一个顾客（编号1、2、3……）
         * 给他一个“抢券的任务” (CouponTask)
         * 把这个任务丢给线程池干
         * 任务返回值存进 results（相当于抢完给你写张小纸条）
         * 每隔100毫秒进来一个顾客（模拟“批量抢购”）
         */
        while(coupon.getCount() > 0){
            Customer customer = new Customer("顾客" + customerId++);
            CouponTask task = new CouponTask(customer, coupon);//任务 = 顾客抢券
            Future<String> future = pool.submit(task); // 提交给线程池跑
            results.add(future);// 保存任务结果

            Thread.sleep(100);//模拟多个用户同时抢购 
        }

        //输出所有抢券结果
        //Future<String> 代表顾客“抢券任务的结果”，用 get() 去拿
        for(Future<String> result : results){
            System.out.println(result.get());
        }

        pool.shutdown();//关闭线程池
    }
}
