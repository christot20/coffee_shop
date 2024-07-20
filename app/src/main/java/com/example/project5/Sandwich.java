package com.example.project5;

import java.util.ArrayList;
import java.util.Objects;

/**
 Sandwich model class used to define a sandwich object.
 @author Thomas Christo, Aditya Pokale
 */
public class Sandwich extends MenuItem {
    private BreadType breadType;
    private ProteinType proteinType;
    private ArrayList<AddOn> addOns;

    /**
     * Constructs a new Sandwich object with the given bread type, protein type, and add-ons.
     * @param breadType the type of bread for the sandwich
     * @param proteinType the type of protein for the sandwich
     * @param addOns the add-ons for the sandwich
     */
    public Sandwich(BreadType breadType, ProteinType proteinType, ArrayList<AddOn> addOns) {
        this.breadType = breadType;
        this.proteinType = proteinType;
        this.addOns = addOns;
    }

    /**
     * Calculates and returns the price of the sandwich.
     * @return the price of the sandwich
     */
    @Override
    public double price() {
        double totalPrice = 0;
        for (AddOn addOn : addOns) {
            totalPrice += addOn.getPrice();
        }
        return totalPrice + proteinType.getPrice();
    }

    /**
     * Sets the bread type of the sandwich.
     * @param breadType the new bread type
     */
    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    /**
     * Sets the protein type of the sandwich.
     * @param proteinType the new protein type
     */
    public void setProteinType(ProteinType proteinType) {
        this.proteinType = proteinType;
    }

    /**
     * Returns the add-ons of the sandwich.
     * @return the add-ons of the sandwich
     */
    public ArrayList<AddOn> getAddOns() {
        return addOns;
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
     * Returns a string representation of the sandwich.
     * @return a string representation of the sandwich
     */
    @Override
    public String toString() {
        String proteinStr = capitalizeWords(proteinType.name());
        String breadStr = capitalizeWords(breadType.name());
        ArrayList<String> addInsStr = new ArrayList<>();
        for (AddOn addOn : addOns) {
            String addOnStr = capitalizeWords(addOn.name());
            addInsStr.add(addOnStr);
        }
        if (addOns.isEmpty()) {
            return "Sandwich(1) [" + breadStr.replace("_", " ") + ", " + proteinStr + "]";
        }
        return "Sandwich(1) [" + breadStr.replace("_", " ") + ", " + proteinStr + ", " + addInsStr + "]";
    }

    /**
     * Checks whether this sandwich is equal to the given object.
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sandwich sandwich = (Sandwich) o;
        return breadType == sandwich.breadType && proteinType == sandwich.proteinType && Objects.equals(addOns, sandwich.addOns);
    }
}

