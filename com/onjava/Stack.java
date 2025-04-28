package com.onjava;

import java.util.*;

public class Stack<T> {

    private Deque<T> storage = new ArrayDeque<>();
    public void push(T v){
        storage.push(v);  //This method is equivalent to {@link #addFirst}
    }

    public T peek(){
        return storage.peek(); //This method is equivalent to {@link #peekFirst()}.
    }

    public T pop(){
        return storage.pop(); //This method is equivalent to {@link #removeFirst()}.
    }

    public boolean isEmpty(){
        return storage.isEmpty();
    }

    @Override
    public String toString(){
        return storage.toString();
    }
}
