package com.example.exp3.thread2;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SalesApp {

    // 定义优惠券的数量
    private static final int TOTAL_COUPONS = 10;

    public static void main(String[] args) throws Exception {
        System.out.println("====== 活动开始，共有" + TOTAL_COUPONS + "张优惠券 ======");
    
        SalesCallable.initCoupons(TOTAL_COUPONS); // 初始化优惠券
    
        ExecutorService executor = new ThreadPoolExecutor(
            2, 4,
            1, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(1),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
        );
    
        List<Future<Boolean>> futures = new ArrayList<>();
        AtomicInteger customerId = new AtomicInteger(0);
        
        // 模拟顾客抢购
        for (int i = 0; i < 100; i++) { // 模拟最多100个顾客
            String customerName = "顾客" + customerId.incrementAndGet();
            Future<Boolean> future = executor.submit(new SalesCallable(customerName));
            futures.add(future);
        }
    
        // 关闭线程池
        executor.shutdown();
        try {
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (Exception e) {
            executor.shutdownNow();
        }
    
        System.out.println("====== 活动结束，所有优惠券已抢完 ======");
    }
}
