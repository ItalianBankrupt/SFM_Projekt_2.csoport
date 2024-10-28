package org.example.Cassa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class IdHandler {

    @FXML
    private Label AmountToPay;
    private Buyer buyer;

    @FXML
    private ListView<String> ListOfIds;

    @FXML
    void AddId(ActionEvent event) {
        ListOfIds.getItems().add(buyer.getName());
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
}
