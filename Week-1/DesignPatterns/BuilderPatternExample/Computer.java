package com.builder;

public class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;
    private String os;

    private Computer(Builder builder) {
        this.cpu     = builder.cpu;
        this.ram     = builder.ram;
        this.storage = builder.storage;
        this.gpu     = builder.gpu;
        this.os      = builder.os;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage +
               ", GPU=" + gpu + ", OS=" + os + "]";
    }

    public static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;
        private String os;

        public Builder cpu(String cpu)         { this.cpu = cpu;         return this; }
        public Builder ram(String ram)         { this.ram = ram;         return this; }
        public Builder storage(String storage) { this.storage = storage; return this; }
        public Builder gpu(String gpu)         { this.gpu = gpu;         return this; }
        public Builder os(String os)           { this.os = os;           return this; }
        public Computer build()                { return new Computer(this); }
    }
}
