package com.example.project5;

import java.util.ArrayList;
import java.util.Objects;

/**
 Coffee model class used to define a coffee object.
 @author Thomas Christo, Aditya Pokale
 */
public class Coffee extends MenuItem {
    private CupSize cupSize;
    private ArrayList<CoffeeAddIn> addIns;
    private int quantity;
    private static final double ADD_IN_PRICE = 0.30;

    /**
     * Constructs a new Coffee object with the given cup size and add-ins.
     * @param cupSize the size of the coffee cup
     * @param addIns the add-ins for the coffee
     */
    public Coffee(CupSize cupSize, ArrayList<CoffeeAddIn> addIns) {
        this.cupSize = cupSize;
        this.addIns = addIns;
        this.quantity = 1;
    }

    /**
     * Calculates and returns the price of the coffee.
     * @return the price of the coffee
     */
    @Override
    public double price() {
        return (cupSize.getPrice() + (ADD_IN_PRICE * addIns.size())) * quantity;
    }

    /**
     * Sets the cup size of the coffee.
     * @param cupSize the new cup size
     */
    public void setCupSize(CupSize cupSize) {
        this.cupSize = cupSize;
    }

    /**
     * Returns the add-ins of the coffee.
     * @return the add-ins of the coffee
     */
    public ArrayList<CoffeeAddIn> getAddIns() {
        return addIns;
    }

    /**
     * Sets the quantity of the coffee.
     * @param quantity the new quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Capitalizes the first letter of each word in the given string.
     * @param str the string to capitalize
     * @return the capitalized string
     */
    private String capitalizeWords(String str) {
        String[] words = str.split("_");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].substring(0, 1).toUpperCase() + words[i].substring(1).toLowerCase();
        }
        return String.join(" ", words);
    }

    /**
     * Returns a string representation of the coffee.
     * @return a string representation of the coffee
     */
    @Override
    public String toString() {
        String cupSizeStr = capitalizeWords(cupSize.name());
        ArrayList<String> addInsStr = new ArrayList<>();
        for (CoffeeAddIn addIn : addIns) {
            String addInStr = capitalizeWords(addIn.name());
            addInsStr.add(addInStr.replace("_", " "));
        }
        if (addIns.isEmpty()) {
            return "Coffee(" + quantity + ") " + cupSizeStr;
        }
        return "Coffee(" + quantity + ") " + cupSizeStr + " " + addInsStr;
    }

    /**
     * Checks whether this coffee is equal to the given object.
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coffee coffee = (Coffee) o;
        return quantity == coffee.quantity &&
                cupSize == coffee.cupSize &&
                Objects.equals(addIns, coffee.addIns);
    }
}
