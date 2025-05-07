package com.collections;

import java.util.*;
import java.nio.file.*;
public class UniqueWordsAlphabetic {

    public static void main(String[] args) throws Exception{
        List<String> lines = Files.readAllLines(Paths.get("com/collections/SetOperations.java"));
        Set<String> words = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);//比较器 --- 忽略大小写进行排序

        for(String line : lines){
            for(String word : line.split("\\W+")){
                if(word.trim().length() > 0){
                    words.add(word);
                }
            }
        }

        System.out.println(words);
    }
}
