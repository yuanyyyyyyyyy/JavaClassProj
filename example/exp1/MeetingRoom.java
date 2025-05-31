package com.example.exp1;

/**
 * 会议室实体类
 */
public class MeetingRoom {
    //实体类中的属性必须是private的
    private String name;//会议室名称
    private int capacity;//会议室容量
    private boolean hasMedia;//isxxx --> 状态位  hasxxx --> 具备

    //构造函数
    public MeetingRoom(String name, int capacity, boolean hasMedia) {
        this.name = name;
        this.capacity = capacity;
        this.hasMedia = hasMedia;
    }

    //setter 和 getter
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public boolean hasMedia(){
        return hasMedia;
    }

    public void setHasMeida(boolean hasMedia){
        this.hasMedia = hasMedia;
    }

    @Override
    public String toString(){
        return "MeetingRoom{" +
               "name = ' " + name + '\'' +
               ", capacity = " + capacity +
               ", hasMedia = " + (hasMedia ? "有" : "无") +
               '}';
    }
}
