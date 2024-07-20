package com.example.project5;

import java.util.ArrayList;

/**
 * Singleton class for managing global data such as the current order and all orders.
 * @author Thomas Christo
 */
public final class GlobalData {
    private static GlobalData globalData; //single instance
    private Order currentOrder;
    private ArrayList<Order> allOrders;

    /**
     * Private constructor to create a GlobalData instance.
     */
    private GlobalData() {
        currentOrder = new Order();
        allOrders = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of GlobalData.
     * @return The singleton instance of GlobalData.
     */
    public static synchronized GlobalData getInstance() {
        if (globalData == null)
            globalData = new GlobalData(); //lazy approach
        return globalData;
    }
    
    /**
     * Returns the current order.
     * @return the current order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * Creates a new current order.
     */
    public void newCurrentOrder() {
        currentOrder = new Order();
    }

    /**
     * Returns all orders.
     * @return all orders
     */
    public ArrayList<Order> getAllOrders() {
        return allOrders;
    }

    /**
     * Adds the given order to the list of all orders.
     * @param order the order to add
     */
    public void placeOrder(Order order) {
        allOrders.add(order);
    }

}