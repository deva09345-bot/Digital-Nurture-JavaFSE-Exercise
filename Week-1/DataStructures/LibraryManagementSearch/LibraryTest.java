package com.library;
// Exercise 5: Library Management Search using Linear and Binary Search
import java.util.Arrays;
import java.util.Comparator;

public class LibraryTest {
    public static void main(String[] args) {
        Book[] books = {
            new Book(1, "Clean Code",        "Robert C. Martin"),
            new Book(2, "Design Patterns",   "GoF"),
            new Book(3, "Effective Java",    "Joshua Bloch"),
            new Book(4, "Refactoring",       "Martin Fowler"),
            new Book(5, "The Pragmatic Programmer", "Hunt & Thomas")
        };

        // Linear search
        System.out.println("Linear Search:");
        Book found = LibrarySearch.linearSearchByTitle(books, "Refactoring");
        System.out.println(found != null ? found : "Not found");

        // Sort then binary search
        Arrays.sort(books, Comparator.comparing(Book::getTitle));
        System.out.println("\nBinary Search:");
        found = LibrarySearch.binarySearchByTitle(books, "Effective Java");
        System.out.println(found != null ? found : "Not found");
    }
}
