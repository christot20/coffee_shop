package com.example.project5;

/**
 MenuItem abstract class used to provide framework for types of items to be ordered.
 @author Thomas Christo, Aditya Pokale
 */
public abstract class MenuItem {

    /**
     * Calculates and returns the price of the menu item.
     * @return the price of the menu item
     */
    public abstract double price(); //subclasses must implement this method

    /**
     * Returns a string representation of the menu item.
     * @return a string representation of the menu item
     */
    @Override
    public abstract String toString(); //subclasses must implement this method

    /**
     * Checks whether this menu item is equal to the given object.
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public abstract boolean equals(Object o); //subclasses must implement this method
}
