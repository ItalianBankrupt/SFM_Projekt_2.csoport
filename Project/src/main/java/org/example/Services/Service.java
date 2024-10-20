package org.example.Services;

import org.example.Restaurant.Restaurant;

public class Service {
    private String name;
    private int price;
    private Services.Type type;

    public Service(String name, int price, Services.Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Services.Type getType() {
        return type;
    }
}
