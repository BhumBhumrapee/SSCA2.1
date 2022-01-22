package io.muic.ooc.fab;

import java.util.Random;

public abstract class Animal {

    private int age;
    private boolean alive;
    // The Animal's position.
    private Location location;
    // The field occupied.
    private Field field;

    // A shared random number generator to control breeding.
    private static final Random RANDOM = new Random();

    protected abstract int getMaxAge();

    protected abstract int getBreedingAge();

    protected abstract double getBreedingProbability();

    protected abstract int getMaxLitterSize();

    public Animal(boolean randomAge, Field field, Location location) {
        this.alive = true;
        this.field = field;
        this.age = 0;
        setLocation(location);
        if (randomAge) {
            this.age = RANDOM.nextInt(getMaxAge());
        }
    }


    /**
     * Increase the age. This could result in the Animal's death
     */
    protected void incrementAge() {
        age++;
        if (age > getMaxAge()) {
            setDead();
        }
    }

    /**
     * Indicate that the rabbit is no longer alive. It is removed from the
     * field.
     */

    protected void setDead() {
        alive = false;
        if (location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Place the fox at the new location in the given field.
     *
     * @param newLocation The fox's new location.
     */

    protected void setLocation(Location newLocation) {
        if (location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * A animal can breed if it has reached the breeding age.
     *
     * @return true if the animal can breed, false otherwise.
     */

    protected boolean canBreed() {
        return getAge() >= getBreedingAge();
    }

    /**
     * Generate a number representing the number of births, if it can breed.
     *
     * @return The number of births (may be zero).
     */
    protected int breed() {
        int births = 0;
        if (canBreed() && RANDOM.nextDouble() <= getBreedingProbability()) {
            births = RANDOM.nextInt(getMaxLitterSize()) + 1;
        }
        return births;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Check whether the rabbit is alive or not.
     *
     * @return true if the rabbit is still alive.
     */

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Location getLocation() {
        return location;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public static Random getRANDOM() {
        return RANDOM;
    }


}
