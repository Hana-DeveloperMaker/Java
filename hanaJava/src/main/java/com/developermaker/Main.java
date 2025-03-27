package com.developermaker;

public class Main {
    private String name;

    Main(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws InterruptedException {
        Start start = new Start();
        start.run();

    }
}