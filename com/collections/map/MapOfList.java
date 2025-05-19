package com.collections.map;

import java.util.*;
import com.typeinfo.pets.*;

public class MapOfList {

    public static final Map<Person, List<? extends Pet>> petPeople = new HashMap<>();
    static{
        petPeople.put(new Person("Dawn"),
                    Arrays.asList(new Cymric("Molly"), new Mutt("Spot"))
        );
        petPeople.put(new Person("Kate"),
                    Arrays.asList(new Cat("Shackleton"), new Cat("Elsie May"), new Dog("Margrett"))
        );
        petPeople.put(new Person("Marilyn"),
                    Arrays.asList(new Pug("Louie aka Louis Snorkelstein Dupree"), new Cat("Stanford "), new Cat("Pinkola"))
        );
        petPeople.put(new Person("Luke"),
                    Arrays.asList(new Rat("Fuzzy"), new Rat("Fizzy"))
        );
    }

    public static void main(String[] args) {
        System.out.println("People: " + petPeople.keySet()); //@return a set view of the keys contained in this map
        System.out.println("Pets: " + petPeople.values());

        for(Person person: petPeople.keySet()){
            System.out.println(person + " has: ");
            for(Pet pet : petPeople.get(person)){
                System.out.println("   " + pet);
            }
        }
    }
}
