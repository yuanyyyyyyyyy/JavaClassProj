package com.collections;

/**
 * 迭代器Iterator
 */
import com.typeinfo.pets.*;
import java.util.*;
public class SimpleIteration {

    public static void main(String[] args) {
        List<Pet> pets = Pets.list(12);
        Iterator<Pet> it = pets.iterator();
        while(it.hasNext()){
            Pet p = it.next();
            System.out.println(p.id() + ":" + p + "");
        }
        System.out.println();
        //A simpler way to iterate through a collection
        for(Pet p : pets){
            System.out.println(p.id() + ":" + p + "");
        }

        it = pets.iterator();//重新创建一个新的迭代器，从头开始遍历
        for(int i = 0; i < 6; i++){
            it.next();
            it.remove();
        }
        System.out.println(pets);
    }
}
