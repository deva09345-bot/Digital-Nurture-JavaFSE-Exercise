package com.inventory;
// Exercise 4: Inventory Management System
public class InventoryTest {
    public static void main(String[] args) {
        InventoryManager mgr = new InventoryManager();

        mgr.addProduct(new Product(1, "Laptop",  50, 999.99));
        mgr.addProduct(new Product(2, "Mouse",  200,  29.99));
        mgr.addProduct(new Product(3, "Keyboard",150,  49.99));

        mgr.updateProduct(1, 45, 949.99);
        mgr.deleteProduct(2);

        System.out.println("\n--- Current Inventory ---");
        mgr.displayAll();
    }
}
