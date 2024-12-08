package com.spa.demo.frontend.Restaurant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CartManager {
    private static final CartManager instance = new CartManager();
    private ObservableList<Checkout> cartItems = FXCollections.observableArrayList();

    private CartManager() {}

    public static CartManager getInstance() {
        return instance;
    }

    public ObservableList<Checkout> getCartItems() {
        return cartItems;
    }

    public void addItemToCart(String foodName, Integer foodAmount, Integer foodPrice) {
        cartItems.add(new Checkout(foodName, foodAmount, foodPrice));
    }
}
