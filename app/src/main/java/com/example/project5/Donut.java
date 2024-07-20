package com.example.project5;

/**
 Donut model class used to define a donut object.
 @author Aditya Pokale
 */
public class Donut extends MenuItem {
    private DonutType type;
    private int quantity;
    private double price;

    /**
     * Constructs a new Donut object with the given donut type.
     * @param type the type of the donut
     */
    public Donut(DonutType type) {
        this.type = type;
        this.quantity = 1;
        this.price = this.type.getPrice();
    }

    /**
     * Calculates and returns the price of the donut.
     * @return the price of the donut
     */
    @Override
    public double price() {
        return type.getPrice() * quantity;
    }

    /**
     * Returns the type of the donut.
     * @return the type of the donut
     */
    public DonutType getType() {
        return type;
    }

    /**
     * Sets the quantity of the donut.
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the donut.
     * @return a string representation of the donut
     */
    @Override
    public String toString() {
        return type.getName() + " Donut (" + quantity + ")";
    }

    /**
     * Checks whether this donut is equal to the given object.
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Donut donut = (Donut) o;
        return quantity == donut.quantity && Double.compare(price, donut.price) == 0
                && type == donut.type;
    }
}

