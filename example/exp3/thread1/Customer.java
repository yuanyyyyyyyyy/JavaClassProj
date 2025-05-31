package com.example.exp3.thread1;

/**
 * 顾客实体类
 */
public class Customer {
    private String name;

    public Customer(String name){
        this.name = name;
    }

    //setter and getter
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
