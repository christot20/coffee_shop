package com.example.project5;

/**
 Enum class used for choice of protein on sandwich.
 @author Thomas Christo, Aditya Pokale
 */
public enum ProteinType {
    BEEF(10.99),
    FISH(9.99),
    CHICKEN(8.99);

    private final double price;

    /**
     * Constructs a new ProteinType with the given price.
     * @param price the price of the protein type
     */
    ProteinType(double price) {
        this.price = price;
    }

    /**
     * Returns the price of the protein type.
     * @return the price of the protein type
     */
    public double getPrice() {
        return this.price;
    }
}
