package com.collections;

/**
 * 添加元素组
 */
import java.util.*;

class Snow{}
class Powder extends Snow{}
class Light extends Powder{}
class Heavy extends Light{}
class Crusty extends Heavy{}
class Slush extends Crusty{}

public class AsListInference {
    
    public static void main(String[] args) {
        List<Snow> snow1 = Arrays.asList(new Crusty(), new Slush(), new Powder());
        //不可变数组snow1,Exception in thread "main" java.lang.UnsupportedOperationException
        //snow1.add(new Heavy());

        List<Snow> snow2 = Arrays.asList(new Light(), new Heavy());
        //Exception
        //snow2.add(new Slush());

        List<Snow> snow3 = new ArrayList<>();
        Collections.addAll(snow3, new Light(), new Heavy(), new Powder());
        //Collection.addAll(snow3, new Crusty(), new Slush());
        snow3.add(new Crusty());

        List<Snow> snow4 = Arrays.<Snow>asList(new Light(), new Heavy(), new Powder());
        //snow4.add(new Crusty()); Exception
    }
}
