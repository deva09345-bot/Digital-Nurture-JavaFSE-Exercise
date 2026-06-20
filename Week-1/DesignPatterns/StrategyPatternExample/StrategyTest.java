package com.strategy;
// Exercise 11: Strategy Pattern
import java.util.Arrays;

public class StrategyTest {
    public static void main(String[] args) {
        Sorter sorter = new Sorter();
        int[] data1 = {5, 3, 8, 1, 9, 2};
        int[] data2 = data1.clone();

        sorter.setStrategy(new BubbleSortStrategy());
        sorter.sort(data1);
        System.out.println(Arrays.toString(data1));

        sorter.setStrategy(new QuickSortStrategy());
        sorter.sort(data2);
        System.out.println(Arrays.toString(data2));
    }
}
