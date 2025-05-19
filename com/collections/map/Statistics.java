package com.collections.map;

import java.util.*;

/**
 * 映射 Map
 */
public class Statistics {

    public static void main(String[] args) {
        Random rand = new Random(47);
        Map<Integer, Integer> m = new HashMap<>();
        for(int i = 0; i < 10000; i++){
            int r = rand.nextInt(20);//生成随机数[0, 20)
            Integer freq = m.get(r); //[1]
            m.put(r, freq == null ? 1 : freq + 1); // V put(K key, V value);
        }

        System.out.println(m);
    }
}
