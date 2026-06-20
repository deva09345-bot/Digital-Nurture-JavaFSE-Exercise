package com.proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Image image = new ProxyImage("photo.jpg");
        System.out.println("-- First call (loads from server) --");
        image.display();
        System.out.println("-- Second call (uses cache) --");
        image.display();
    }
}
