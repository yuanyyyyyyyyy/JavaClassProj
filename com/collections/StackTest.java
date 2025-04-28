package com.collections;

/**
 * 堆栈 Stack
 */
import java.util.*;
public class StackTest {

    public static void main(String[] args) {
        Deque<String> stack = new ArrayDeque<>();
        for(String s : "My dog has freas".split(" ")){
            stack.push(s);
        }

        while(!stack.isEmpty()){
            System.out.print(stack.pop() + " ");
        }
    }
}
