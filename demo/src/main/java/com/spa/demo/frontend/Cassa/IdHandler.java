package com.spa.demo.frontend.Cassa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class IdHandler {

    @FXML
    private Label AmountToPay;
    private Buyer buyer;
    private List<String> Ids = new ArrayList<>();

    @FXML
    private ListView<String> ListOfIds;

    @FXML
    void AddId(ActionEvent event) {
        ListOfIds.getItems().add(buyer.GenerateId());
    }

    @FXML
    void AddServices(ActionEvent event) {

    }

    @FXML
    void AddTickets(ActionEvent event) {

    }

    @FXML
    void RemoveId(ActionEvent event) {

    }

    public void sendBuyerInfos(Buyer buyer)
    {
        this.buyer = buyer;
    }

    public void initialization()
    {

    }
}
