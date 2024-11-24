package com.spa.demo.frontend.Restaurant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class identificationController {

    @FXML
    private Label bandID1;

    @FXML
    private Label bandID2;

    @FXML
    private Label bandID3;

    @FXML
    private Label bandID4;

    @FXML
    private Label bandID5;

    @FXML
    private Label bandValue1;

    @FXML
    private Label bandValue2;

    @FXML
    private Label bandValue3;

    @FXML
    private Label bandValue4;

    @FXML
    private Label bandValue5;

    @FXML
    private GridPane panelGridPane;

    @FXML
    private TextField idBox;

    @FXML
    private TableView<?> smallBasket;


    @FXML
    void loadCoffe(ActionEvent event) {

    }

    @FXML
    void loadDessert(ActionEvent event) {

    }

    @FXML
    void loadDrinks(ActionEvent event) {
    }

    @FXML
    void loadMainCourse(ActionEvent event) {

    }

    @FXML
    void loadPreFood(ActionEvent event) {
    }

    @FXML
    void loadSoup(ActionEvent event) {
    }

    @FXML
    void loadRestaurantItems(String category){

    }

    @FXML
    void idCheck(ActionEvent event) {
        //ID ellenőrzés
    }

}
