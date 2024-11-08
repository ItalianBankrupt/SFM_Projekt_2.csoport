package com.spa.demo.frontend.Cassa;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class IdHandler {

    @FXML
    private Button AddId;
    private Label AmountToPay;
    private Buyer buyer;
    private String RemoveId = "";

    @FXML
    private ListView<String> ListOfIds;

    @FXML
    void AddId(ActionEvent event) {
        buyer.GenerateId();
    }

    @FXML
    void AddServices(ActionEvent event) {

    }

    @FXML
    void AddTickets(ActionEvent event) {

    }

    @FXML
    void RemoveId(ActionEvent event) {
        RemoveId = ListOfIds.getSelectionModel().getSelectedItem();
        buyer.RemoveId(RemoveId);
        buyer.setNumberOfGeneratedId(buyer.getNumberOfGeneratedId()- 1);
    }

    @FXML
    void changeBuyerInfos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CassaGUI/AddCustomer.fxml"));
        Parent root = loader.load();
        AddCustomer addCustomer = loader.getController();
        addCustomer.SendBuyerToAddCustomer(buyer);

        Stage stage = new Stage();
        stage.setTitle("Add Customer");
        stage.setScene(new Scene(root));
        stage.show();

        //close this window
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }


    public void sendBuyerInfos(Buyer anotherBuyer)
    {
        this.buyer = anotherBuyer;
    }

    public void initialization()
    {
        if(buyer.getIds().size() == 0) {
            AddId.fire();
        }
        ListOfIds.setItems(buyer.getIds());
    }
}
