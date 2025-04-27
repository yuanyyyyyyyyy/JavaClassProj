//: typeinfo/pets/Pets.java
// Facade to produce a default PetCreator.
package com.typeinfo.pets;
import java.util.*;

public class Pets {
  public static final PetCreator creator = new LiteralPetCreator();

  //获取一个随机宠物
  public static Pet get() {
    return creator.randomPet();
  }
  public static Pet[] createArray(int size) {
    return creator.createArray(size);
  }
  public static ArrayList<Pet> arrayList(int size) {
    return creator.arrayList(size);
  }

  //自加
  private static Random rand = new Random(47);

  // 模拟一个“宠物池子”
  private static List<Class<? extends Pet>> types = List.of(
    Dog.class, Cat.class, Hamster.class
  );
  public static List<Pet> list(int size) {
    List<Pet> result = new ArrayList<>();
    for (int i = 0; i < size; i++) {
        int idx = rand.nextInt(types.size());
        try {
            result.add(types.get(idx).getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    return result;
  }

} ///:~
