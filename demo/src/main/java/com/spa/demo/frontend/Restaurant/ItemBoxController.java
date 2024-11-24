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

}
