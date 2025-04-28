package com.collections;

public class StackCollision {

    public static void main(String[] args) {
        com.onjava.Stack<String> stack = new com.onjava.Stack<>();
        for(String s : "My dog has fleas".split(" ")){
            stack.push(s);
        }
        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
        System.out.println();

        java.util.Stack<String> stack2 = new java.util.Stack<>();
        for(String s : "My dog has fleas".split(" ")){
            stack2.push(s);
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop() + " ");
        }
    }
}
