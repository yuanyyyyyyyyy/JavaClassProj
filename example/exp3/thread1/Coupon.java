package com.example.exp3.thread1;

/**
 * 优惠券实体类
 */
public class Coupon {

    private int count; // 优惠券数量

    public Coupon(int count) {
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    // 抢券，减少优惠券数量
    public int decrement(){
        if(count > 0){
            return --count;
        }
        return 0;
    }
}
