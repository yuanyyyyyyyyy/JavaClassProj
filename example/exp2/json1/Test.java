package com.example.exp2.json1;

public class Test {
    public static void main(String[] args) throws Exception {
        // 对象转JSON测试
        Person person = new Person();
        person.setName("Alice");
        person.setAge(25);
        person.setStudent(true);
        String json = ToObjectMapper.toJson(person);
        System.out.println(json); // 输出: {"name":"Alice","age":25,"isStudent":true}

        // JSON转对象测试
        String jsonInput = "{\"name\":\"Bob\", \"age\":30, \"isStudent\":false}";
        Person newPerson = FromObjectMapper.fromJson(jsonInput, Person.class);
        System.out.println(newPerson.getName());     // 输出: Bob
        System.out.println(newPerson.getAge());      // 输出: 30
        System.out.println(newPerson.isStudent());   // 输出: false
    }
}

class Person {
    private String name;
    private int age;
    private boolean isStudent;

    public Person() {}

    // Getter和Setter方法
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public boolean isStudent() { return isStudent; }
    public void setStudent(boolean student) { isStudent = student; }
}