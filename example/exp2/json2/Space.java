package com.example.exp2.json2;

public class Space {
 
    private int id;//编号

    private String name;//名称

    public Space(){

    }

    public Space(int id, String name) {
        this.id = id;   
        this.name = name;
    }

    @Override
    public String toString() {
        return "Space{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * Space对象JSON字符串样式
     * {"id":1,"name":"北京"}
     * JSON:数值、字符串、布尔值、数组、对象
     * JSON对象可以嵌套定义
     * JSON对象可以是key-value键值对,中间使用冒号（:）分隔
     * JSON对象是“{}"
     * JSON数组是“[]”
     */

     
}
