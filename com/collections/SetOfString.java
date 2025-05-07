package com.collections;

import java.util.*;

public class SetOfString {

    public static void main(String[] args) {
        //Set<String> colors = new HashSet<>();
        Set<String> colors = new TreeSet<>();// TreeSet sorts the elements in ascending order
        for(int i = 0; i < 100; i++){
            colors.add("Yellow");
            colors.add("Blue"); 
            colors.add("Red"); 
            colors.add("Red"); 
            colors.add("Orange"); 
            colors.add("Yellow"); 
            colors.add("Blue"); 
            colors.add("Purple");
        }

        System.out.println(colors);
       
    }
}
