package com.example.project5;

/**
 * Enumeration for the types of donuts user can order.
 * @author Aditya Pokale
 */
public enum DonutType {
    CHOCOLATE_YEAST (1.79, "Chocolate Yeast", R.drawable.chocolate_yeast),
    VANILLA_YEAST (1.79, "Vanilla Yeast", R.drawable.vanilla_yeast),
    STRAWBERRY_YEAST (1.79, "Strawberry Yeast", R.drawable.strawberry_yeast),
    BLUEBERRY_YEAST (1.79, "Blueberry Yeast", R.drawable.blueberry_yeast),
    RASPBERRY_YEAST (1.79, "Raspberry Yeast", R.drawable.raspberry_yeast),
    APPLE_YEAST (1.79, "Apple Yeast", R.drawable.apple_yeast),
    SPRINKLES_CAKE(1.89, "Sprinkles Cake", R.drawable.sprinkle_cake),
    OREO_CAKE(1.89, "Oreo Cake", R.drawable.oreo_cake),
    KITKAT_CAKE(1.89, "KitKat Cake", R.drawable.kitkat_cake),
    JELLY_CAKE(1.89, "Jelly Cake", R.drawable.jelly_cake),
    GLAZED_HOLE(0.39, "Glazed Hole", R.drawable.glazed_hole),
    CINNAMON_HOLE(0.39, "Cinnamon Hole", R.drawable.cinnamon_hole),
    MAPLE_HOLE(0.39, "Maple Hole", R.drawable.maple_hole),
    HONEY_HOLE(0.39, "Honey Hole", R.drawable.honey_hole);

    private final double price;
    private final String name;
    private final int image;

    /**
     * Constructs a new DonutType with the given price.
     * @param price the price of the donut type
     */
    DonutType(double price, String name, int image) {
        this.price = price;
        this.name = name;
        this.image= image;
    }

    /**
     * Returns the price of the donut type.
     * @return the price of the donut type
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns the name of the donut type.
     * @return the name of the donut type
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the image of the donut type.
     * @return the image of the donut type
     */
    public int getImage() {
        return this.image;
    }
}


