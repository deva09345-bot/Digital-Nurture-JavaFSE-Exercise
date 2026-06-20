package com.observer;
// Exercise 8: Observer Pattern
public class ObserverTest {
    public static void main(String[] args) {
        StockMarket market = new StockMarket("AAPL");

        Observer client1 = new StockClient("Alice");
        Observer client2 = new StockClient("Bob");

        market.register(client1);
        market.register(client2);

        market.setPrice(189.50);
        market.setPrice(192.30);

        market.deregister(client2);
        market.setPrice(195.00);   // Only Alice notified
    }
}
