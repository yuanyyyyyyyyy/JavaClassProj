package com.example.exp2.json2;

import java.lang.reflect.Field;

/**
 * Java反射
 */
public class ObjectMapper {   
    /**
     * 对象转JSON字符串
     */
    public static String toJSON(Object obj) 
    {
        try{
            //通过反射获取运行时Class对象的类
            Class objClass = obj.getClass();

            //定义JSON字符串的内容
            StringBuilder json = new StringBuilder();
            //JSON对象开始
            json.append("{");
            /**
            * 遍历运行时Class对象的属性
            * 获得属性名作为JSON对象的key,属性的值作为JSON对象的value
            * 这里只做基础数据类型的属性
            * 复杂数据类型(List,Map,Array)如何处理(多层结构嵌套处理)
            * (1)成为大神 (2)使用第三方库(Jackson, FastJSON2, Gson)
            */
            Field[] fields = objClass.getDeclaredFields();//获得所有属性（包括私有属性）
            //objClass.getFields();
            //fori ~ 对应数组
            //foreach ~ 对象集合，就是“迭代”
        
            for(int i = 0; i < fields.length; i++){

                //获得属性对象
                Field field = fields[i];
                //破坏面向对象的封装特性，对私有属性进行访问，通常产生常说的“安全漏洞”
                field.setAccessible(true);
                //设置JSON字符串的key
                json.append("\"").append(field.getName()).append("\" : ");
                //设置JSON字符串的value,只处理基本数据类型
                //判断是不是字符，字符串需要增加双引号
                if(field.getType() == String.class){
                    json.append("\"").append(field.get(obj)).append("\"");
                }else{
                    json.append(field.get(obj));
                }

                //更复杂需要拼接｛｝和 [] 
                //JSON对象属性间分隔符，fields对象最后一个属性不需要逗号
                if(i < fields.length - 1){
                    json.append(",");
                }
            }
            //jSON对象结束
            json.append("}");

            return json.toString();
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }


    //JSON字符串转对象
    //要处理的对象类型未知，使用泛型
    public static <T> T fromJSON(String json, Class<T> clazz){
        try{
            //利用反射创建运行时类对象
            //T obj = clazz.getConstructor().newInstance();//public构造方法
            T obj = clazz.getDeclaredConstructor().newInstance();//可访问private对象
            //从JSON字符串中获取所有的属性和对应的属性值
            //去掉开始的大括号和最后的大括号后，使用逗号拆分字符串
            String[] keyValues = json.substring(1, json.length() - 1).split(",");
            //遍历属性和属性值
            for(String keyValue : keyValues){
                //依据JSON格式分割Key和Value
                String[] pair = keyValue.split(":");
                //获得属性名的键值对
                String fieldName = pair[0].trim().replaceAll("\"","");
                String fieldValue = pair[1].trim();
                //获得对象对应的熟悉行知
                Field field = clazz.getDeclaredField(fieldName);
                //破坏面向对象的封装特性，对私有属性进行访问，通常产生常说的“安全漏洞”
                field.setAccessible(true);
                //设置属性值
                if(field.getType() == String.class){//基础类型：字符串
                    //去掉JSON字符串中的双引号
                    field.set(obj, fieldValue.replaceAll("\"", ""));
                }else if(field.getType() == int.class ){//基础类型：整数
                    field.set(obj, Integer.parseInt(fieldValue));
                }else if(field.getType() == boolean.class){//基础类型：布尔类型
                    field.set(obj, Boolean.parseBoolean(fieldValue));
                }
            }

            return obj;
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
