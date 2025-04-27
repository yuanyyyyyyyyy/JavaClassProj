package com.collections;

import com.typeinfo.pets.*;
import java.util.*;

public class CrossCollectionIteration {

    // public static void main(String[] args) {
    //     List<Pet> pets = Pets.list(8);
    //     LinkedList<Pet> petsLL = new LinkedList<Pet>(pets);
    //     HashSet<Pet> petsHS = new HashSet<Pet>(pets);
    //     TreeSet<Pet> petsTS = new TreeSet<Pet>(pets);
    //     display(pets.iterator());
    //     display(petsLL.iterator());
    //     display(petsHS.iterator());
    //     display(petsTS.iterator());

    // }

    // public static void display(Iterator<Pet> it){
    //     while(it.hasNext()){
    //         Pet p = it.next();
    //         System.out.println(p.id() + ":" + p + "");
    //     }
    //     System.out.println();
    // }

    /**
     * 更简洁版本
     * @param args
     */
    public static void main(String[] args) {
        List<Pet> pets = Pets.list(8);
        LinkedList<Pet> petsLL = new LinkedList<Pet>(pets);
        HashSet<Pet> petsHS = new HashSet<Pet>(pets);
        TreeSet<Pet> petsTS = new TreeSet<Pet>(pets);
        display(pets);
        display(petsLL);
        display(petsHS);
        display(petsTS);

    }


    public static void display(Iterable<Pet> ip){
        Iterator<Pet> it = ip.iterator();
        while(it.hasNext()){
            Pet p = it.next();
            System.out.println(p.id() + ":" + p + "");
        }
        System.out.println();
    }
}
