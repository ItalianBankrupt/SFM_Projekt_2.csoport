package com.spa.demo.frontend.Restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

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
    private Spinner<Integer> prodQuantity;

    public void initialize(){
      SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 8);
      valueFactory.setValue(0);
      prodQuantity.setValueFactory(valueFactory);
    }

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

    public void setProdQuantity(Spinner<Integer> prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    @FXML
    void addToCart(ActionEvent event) throws IOException {
        if(prodQuantity.getValue()>0){
            CartManager.getInstance().addItemToCart(prodName.getText(), prodQuantity.getValue(),
                    Integer.parseInt(prodPrice.getText().replace("Ft", ""))*prodQuantity.getValue());
        }

    }

}

