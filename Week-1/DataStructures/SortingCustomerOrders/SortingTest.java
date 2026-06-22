package com.sorting;
// Exercise 6: Sorting Customer Orders using Sorting Algorithms
import java.util.Arrays;

public class SortingTest {
    public static void main(String[] args) {
        Order[] orders1 = {
            new Order(1, "Alice", 250.00),
            new Order(2, "Bob",    89.99),
            new Order(3, "Carol", 540.50),
            new Order(4, "Dave",  175.00)
        };
        Order[] orders2 = orders1.clone();

        SortOrders.bubbleSort(orders1);
        System.out.println("Bubble Sort:");
        Arrays.stream(orders1).forEach(System.out::println);

        SortOrders.quickSort(orders2, 0, orders2.length - 1);
        System.out.println("\nQuick Sort:");
        Arrays.stream(orders2).forEach(System.out::println);
    }
}
