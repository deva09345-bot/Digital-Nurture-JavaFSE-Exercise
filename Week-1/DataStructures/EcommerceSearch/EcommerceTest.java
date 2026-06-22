package com.ecommerce;
// Exercise 1: E-Commerce Product Search
import java.util.Arrays;
import java.util.Comparator;

public class EcommerceTest {
    public static void main(String[] args) {
        Product[] products = {
            new Product(3, "Camera",  "Electronics"),
            new Product(1, "Laptop",  "Electronics"),
            new Product(5, "Monitor", "Electronics"),
            new Product(2, "Mouse",   "Accessories"),
            new Product(4, "Tablet",  "Electronics")
        };

        // Linear search (unsorted)
        System.out.println("Linear Search:");
        Product found = SearchAlgorithms.linearSearch(products, "Mouse");
        System.out.println(found != null ? found : "Not found");

        // Sort by name then binary search
        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        System.out.println("\nBinary Search:");
        found = SearchAlgorithms.binarySearch(products, "Monitor");
        System.out.println(found != null ? found : "Not found");
    }
}
