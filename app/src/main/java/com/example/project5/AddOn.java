package com.example.project5;

/**
 * Enum representing the different types of add-ons available for a sandwich.
 * Each add-on has an associated price.
 * @author Thomas Christo, Aditya Pokale
 */
public enum AddOn {
    LETTUCE(0.30),
    TOMATO(0.30),
    ONION(0.30),
    CHEESE(1.00);

    private final double price;

    /**
     * Constructs a new AddOn with the given price.
     * @param price the price of the add-on
     */
    AddOn(double price) {
        this.price = price;
    }

    /**
     * Returns the price of the add-on.
     * @return the price of the add-on
     */
    public double getPrice() {
        return price;
    }
}
