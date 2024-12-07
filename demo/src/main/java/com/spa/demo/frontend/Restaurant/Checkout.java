package com.spa.demo.frontend.Restaurant;

public class Checkout {
    String foodName;
    int foodAmount;
    int foodPrice;

    public Checkout(String foodName, int foodAmount, int foodPrice) {
        this.foodName = foodName;
        this.foodAmount = foodAmount;
        this.foodPrice = foodPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }
}
