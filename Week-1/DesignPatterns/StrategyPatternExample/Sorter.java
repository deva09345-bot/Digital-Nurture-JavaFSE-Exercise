package com.strategy;

public class Sorter {
    private SortStrategy strategy;
    public void setStrategy(SortStrategy strategy) { this.strategy = strategy; }
    public void sort(int[] data) { strategy.sort(data); }
}
