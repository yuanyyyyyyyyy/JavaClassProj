package com.collections;
import com.typeinfo.pets.*;
import java.util.*;

public class ListIteration {
    
    public static void main(String[] args) {
        List<Pet> pets = Pets.list(8);
        ListIterator<Pet> it = pets.listIterator();
        //Iterator<Pet> it2 = pets.iterator();
        while(it.hasNext()){
            System.out.println(it.next() +
                ", " + it.nextIndex() + 
                ", " + it.previousIndex() + "; ");
        }
        System.out.println();

        while(it.hasPrevious()){
            System.out.println(it.previous().id() + " ");
        }
        System.out.println();
        System.out.println(pets);

        it = pets.listIterator(3);
        while(it.hasNext()){
            it.next();
            it.set(Pets.get());
        }
        System.out.println(pets);

    }
}
