package com.spa.demo.frontend.Restaurant;

public class Checkout {
    String foodName;
    int foodAmount;
    int foodPrice;

    public String getFoodMessage() {
        return foodMessage;
    }

    public void setFoodMessage(String foodMessage) {
        this.foodMessage = foodMessage;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
    }

    String foodMessage;
    int bandID;

    public Checkout(String foodName, int foodAmount, int foodPrice) {
        this.foodName = foodName;
        this.foodAmount = foodAmount;
        this.foodPrice = foodPrice;
    }

    public void finalCheckout(String foodMessage, int bandID)
    {
        this.foodMessage = foodMessage;
        this.bandID = bandID;
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
