package org.example.Restaurant;

public class Food {
    private String name;
    private int price;
    private Restaurant.Type type;

    public Food(String name, int price, Restaurant.Type type) {
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

    public Restaurant.Type getType() {
        return type;
    }
}
