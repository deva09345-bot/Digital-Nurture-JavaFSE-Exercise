package com.command;

public class Light {
    private String location;
    public Light(String location) { this.location = location; }
    public void on()  { System.out.println(location + " light ON"); }
    public void off() { System.out.println(location + " light OFF"); }
}
