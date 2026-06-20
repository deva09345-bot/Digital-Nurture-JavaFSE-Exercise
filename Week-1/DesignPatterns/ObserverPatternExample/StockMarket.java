package com.observer;

import java.util.ArrayList;
import java.util.List;

public class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public StockMarket(String stockName) { this.stockName = stockName; }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override public void register(Observer o)   { observers.add(o); }
    @Override public void deregister(Observer o) { observers.remove(o); }
    @Override public void notifyObservers() {
        for (Observer o : observers) o.update(stockName, price);
    }
}
