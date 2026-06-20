package com.observer;

public class StockClient implements Observer {
    private String clientName;
    public StockClient(String clientName) { this.clientName = clientName; }

    @Override
    public void update(String stockName, double price) {
        System.out.println(clientName + " notified: " + stockName + " = $" + price);
    }
}
