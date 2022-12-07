package com.example.ex_bags.Models;

public class Statistic {

    private long brand;
    private String name;

    public Statistic() {
    }

    public Statistic(long brand, String name) {
        this.brand = brand;
        this.name = name;
    }

    public long getBrand() {
        return brand;
    }

    public void setBrand(long brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
