package com.example.project5;

import java.util.ArrayList;
import java.util.Objects;

/**
 Order model class used to define an Order object for user orders.
 @author Thomas Christo, Aditya Pokale
 */
public class Order {
    private int orderNumber;
    private ArrayList<MenuItem> menuItems;
    private static int NEXT_ORDER_NUMBER = 1;

    /**
     * Constructs a new Order object with a unique order number and an empty list of menu items.
     */
    public Order() {
        this.orderNumber = NEXT_ORDER_NUMBER++;
        this.menuItems = new ArrayList<>();
    }

    /**
     * Returns the order number of this order.
     * @return the order number
     */
    public int getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * Returns the menu items in this order.
     * @return the menu items
     */
    public ArrayList<MenuItem> getMenuItems() {
        return this.menuItems;
    }

    /**
     * Returns a string representation of this order (the order number).
     * @return a string representation of this order (the order number).
     */
    @Override
    public String toString() {
        return String.valueOf(orderNumber);
    }

    /**
     * Checks whether this order is equal to the given object.
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderNumber == order.orderNumber && Objects.equals(menuItems, order.menuItems);
    }
}
