package com.spa.demo.frontend.Restaurant;

public class CheckOutFinal{

    public void setID(String ID) {
        this.ID = ID;
    }

    String foodName;
    int foodAmount;
    int foodPrice;
    String ID;

    public String getFoodName() {
        return foodName;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public String getID() {
        return ID;
    }

    public CheckOutFinal(String foodName, int foodAmount, int foodPrice, String ID) {
        this.foodName = foodName;
        this.foodAmount = foodAmount;
        this.foodPrice = foodPrice;
        this.ID = ID;
    }



}