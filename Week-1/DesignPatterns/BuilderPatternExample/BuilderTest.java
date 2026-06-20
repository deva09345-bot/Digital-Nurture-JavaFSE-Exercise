package com.builder;
// Exercise 1: Adapter Pattern
public class BuilderTest {
    public static void main(String[] args) {
        Computer gamingPC = new Computer.Builder()
            .cpu("Intel Core i9")
            .ram("32GB DDR5")
            .storage("2TB NVMe SSD")
            .gpu("NVIDIA RTX 4090")
            .os("Windows 11")
            .build();

        Computer officePC = new Computer.Builder()
            .cpu("Intel Core i5")
            .ram("16GB DDR4")
            .storage("512GB SSD")
            .os("Windows 10")
            .build();

        System.out.println(gamingPC);
        System.out.println(officePC);
    }
}
