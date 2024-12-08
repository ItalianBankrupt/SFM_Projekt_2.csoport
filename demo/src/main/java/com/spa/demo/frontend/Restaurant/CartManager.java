package com.spa.demo.frontend.Restaurant;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CartManager {
    private static final CartManager instance = new CartManager();
    private ObservableList<Checkout> cartItems = FXCollections.observableArrayList();
    private ObservableList<CheckOutFinal> checkoutItems = FXCollections.observableArrayList();

    private CartManager() {}

    public static CartManager getInstance() {
        return instance;
    }

    public ObservableList<Checkout> getCartItems() {
        return cartItems;
    }

    public ObservableList<CheckOutFinal> getCheckOutItems() {
        return checkoutItems;
    }

    public void addItemToCart(String foodName, Integer foodAmount, Integer foodPrice) {
        cartItems.add(new Checkout(foodName, foodAmount, foodPrice));
        checkoutItems.add(new CheckOutFinal(foodName,foodAmount,foodPrice,""));
    }

}
