package com.spa.demo.frontend.Restaurant;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ItemBoxController {

    @FXML
    private AnchorPane cardForm;

    @FXML
    private ImageView prodImage;

    @FXML
    private Label prodName;

    @FXML
    private Label prodPrice;

    @FXML
    private Spinner<?> prodQuantity;

    public ItemBoxController() {

    }

    public AnchorPane getCardForm() {
        return cardForm;
    }

    public void setCardForm(AnchorPane cardForm) {
        this.cardForm = cardForm;
    }

    public ImageView getProdImage() {
        return prodImage;
    }

    public void setProdImage(ImageView prodImage) {
        this.prodImage = prodImage;
    }

    public Label getProdName() {
        return prodName;
    }

    public void setProdName(Label prodName) {
        this.prodName = prodName;
    }

    public Label getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(Label prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Spinner<?> getProdQuantity() {
        return prodQuantity;
    }

    public void setProdQuantity(Spinner<?> prodQuantity) {
        this.prodQuantity = prodQuantity;
    }
}
