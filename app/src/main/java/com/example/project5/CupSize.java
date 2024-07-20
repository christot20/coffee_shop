package com.example.project5;

/**
 Enum class used for choice of cup size for coffee.
 @author Thomas Christo, Aditya Pokale
 */
public enum CupSize {
    SHORT(1.99),
    TALL(2.49),
    GRANDE(2.99),
    VENTI(3.49);

    private final double price;

    /**
     * Constructs a new CupSize with the given price.
     * @param price the price of the cup size
     */
    CupSize(double price) {
        this.price = price;
    }

    /**
     * Returns the price of the cup size.
     * @return the price of the cup size
     */
    public double getPrice() {
        return this.price;
    }
}
