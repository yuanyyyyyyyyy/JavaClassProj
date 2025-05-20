package com.collections.queue;

/**
 * 队列 Queue
 * 将LinkedList向上转型到Queue
 */
import java.util.*;
public class QueueDemo {

    public static void printQ(Queue queue){
        while(queue.peek() != null){ // peek()返回队头元素，但不删除
            System.out.println(queue.remove());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Random rand = new Random(47);
        for(int i = 0; i < 10; i++){
            queue.offer(rand.nextInt(i + 10));//offer()添加一个元素并返回true
        }
        printQ(queue);

        Queue<Character> qc = new LinkedList<>();
        for(char c : "Brontsaurus".toCharArray()){ // Converts this string to a new character array.
            qc.offer(c);
        }
        printQ(qc);
    }
}
