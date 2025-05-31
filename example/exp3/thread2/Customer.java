package com.example.exp3.thread2;

/**
 * 顾客类
 */
public class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


/**
 * 实验三：多线程编程：采用java.util.concurrent包中的多线程技术
 * JDK 1.0  Thread Runnable 多线程同步执行比较难控制
 *              游戏、网络通信、数据库连接池
 * JDK 1.5  Callable Future ExecutorService 多线程同步执行比较容易控制
 *              原子类 AtomicXXX
 * 
 *       Callable接口与Runnable接口类似，区别在于：
 *              Callable接口的call()方法可以返回值和抛出异常，
 *              Runnable接口的run()方法没有返回值，不能抛出异常。
 *       Futrue接口表示异步计算的结果。它提供了检查计算是否完成的方法，提供获取计算结果的方法。
 *       ExecutorService表示一个线程池，用于管理线程的创建与销毁。 
 *             ThreadPoolExecutor类实现了ExecutorService接口。
 *             Executors.newFixedThreadPool()方法返回一个固定线程数量的线程池。
 *             Executors.newCachedThreadPool()方法返回一个可缓存的线程池。
 *             Executors.newSingleThreadExecutor()方法返回一个只有一个线程的线程池。   
 *             Executors.newScheduledThreadPool()方法返回一个定时及周期执行的线程池。 
 *       原子类AtomicXXX 线程安全
 *              AtomicBoolean 原子更新布尔类型
 *              AtomicInteger 原子更新整型
 *            AtomicInteger.IncrementAndGet() 原子更新整型并返回更新后的值
 *            AtomicInteger.getAndIncrement() 原子更新整型并返回更新前的值
 *            AtomicInteger.get()  获取当前值
 *            AtomicInteger.compareAndSet() 比较并设置新的值
 * 
 */
