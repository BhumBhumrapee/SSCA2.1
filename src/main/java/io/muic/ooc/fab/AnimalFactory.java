package io.muic.ooc.fab;

import java.util.HashMap;
import java.util.Map;

public class AnimalFactory {
    public static final Map<AnimalType, Class<? extends Animal>> ANIMAL_MAPPING = new HashMap<>();

    static {
        ANIMAL_MAPPING.put(AnimalType.RABBIT, Rabbit.class);
        ANIMAL_MAPPING.put(AnimalType.FOX, Fox.class);
    }

    public static Animal createAnimal(AnimalType animalType, boolean randomAge, Field field, Location location) {
        try {
            return ANIMAL_MAPPING.get(animalType).getDeclaredConstructor(boolean.class, Field.class, Location.class).newInstance(randomAge, field, location);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        /*

        if (animalType.equals(AnimalType.RABBIT)) {
            return new Rabbit(randomAge, field, location);
        } else if (animalType.equals(AnimalType.FOX)) {
            return new Fox(randomAge, field, location);
        } else {
            throw new RuntimeException("Unknown animal Type");
        }
         */
    }

}
