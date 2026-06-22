package com.strategy;

public class QuickSortStrategy implements SortStrategy {
    @Override
    public void sort(int[] data) {
        quickSort(data, 0, data.length - 1);
        System.out.println("Sorted with QuickSort");
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++)
            if (arr[j] <= pivot) { i++; int t = arr[i]; arr[i] = arr[j]; arr[j] = t; }
        int t = arr[i+1]; arr[i+1] = arr[high]; arr[high] = t;
        return i + 1;
    }
}
