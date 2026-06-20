package com.inventory;

import java.util.HashMap;
import java.util.Map;

// HashMap gives O(1) average for add/update/delete
public class InventoryManager {
    private Map<Integer, Product> inventory = new HashMap<>();

    public void addProduct(Product p) {
        inventory.put(p.getProductId(), p);
        System.out.println("Added: " + p);
    }

    public void updateProduct(int id, int quantity, double price) {
        Product p = inventory.get(id);
        if (p != null) {
            p.setQuantity(quantity);
            p.setPrice(price);
            System.out.println("Updated: " + p);
        } else {
            System.out.println("Product ID " + id + " not found.");
        }
    }

    public void deleteProduct(int id) {
        Product removed = inventory.remove(id);
        System.out.println(removed != null ? "Deleted: " + removed : "Product not found.");
    }

    public void displayAll() {
        inventory.values().forEach(System.out::println);
    }
}
