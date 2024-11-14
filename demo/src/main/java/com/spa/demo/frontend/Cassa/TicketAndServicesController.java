package com.spa.demo.frontend.Cassa;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TicketAndServicesController {

    @FXML
    private ListView<String> Ids;

    @FXML
    void addServices(ActionEvent event) {

    }

    @FXML
    void addTickets(ActionEvent event) {

    }

    public void receiveListOfIds(ObservableList<String> ListOfIds)
    {
        Ids.setItems(ListOfIds);
    }

}
