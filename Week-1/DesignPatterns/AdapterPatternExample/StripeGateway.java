package com.adapter;

public class StripeGateway {
    public void charge(double amount) {
        System.out.println("Stripe: Charging $" + amount);
    }
}
