    package com.ecommerce;

    // Linear search: O(n) | Binary search: O(log n) on sorted array
    public class SearchAlgorithms {

        // Linear Search - works on unsorted arrays
        public static Product linearSearch(Product[] products, String name) {
            for (Product p : products) {
                if (p.getProductName().equalsIgnoreCase(name)) return p;
            }
            return null;
        }

        // Binary Search - array must be sorted by productName
        public static Product binarySearch(Product[] products, String name) {
            int low = 0, high = products.length - 1;
            while (low <= high) {
                int mid = (low + high) / 2;
                int cmp = products[mid].getProductName().compareToIgnoreCase(name);
                if (cmp == 0) return products[mid];
                else if (cmp < 0) low  = mid + 1;
                else              high = mid - 1;
            }
            return null;
        }
    }
