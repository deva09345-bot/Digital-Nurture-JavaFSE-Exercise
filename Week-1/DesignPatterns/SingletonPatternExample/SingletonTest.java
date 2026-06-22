package com.singleton;
// Exercise 10: Singleton Pattern
public class SingletonTest {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("First log message");
        logger2.log("Second log message");

        // Verify same instance
        System.out.println("Same instance? " + (logger1 == logger2)); // true
    }
}
