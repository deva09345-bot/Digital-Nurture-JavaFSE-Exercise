package com.proxy;

public class ProxyImage implements Image {
    private String filename;
    private RealImage realImage;   // lazy-loaded + cached

    public ProxyImage(String filename) { this.filename = filename; }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);   // load once
        }
        realImage.display();
    }
}
