package com.library;

public class LibrarySearch {

    // Linear Search O(n)
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book b : books)
            if (b.getTitle().equalsIgnoreCase(title)) return b;
        return null;
    }

    // Binary Search O(log n) - books must be sorted by title
    public static Book binarySearchByTitle(Book[] books, String title) {
        int low = 0, high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].getTitle().compareToIgnoreCase(title);
            if (cmp == 0)      return books[mid];
            else if (cmp < 0)  low  = mid + 1;
            else               high = mid - 1;
        }
        return null;
    }
}
