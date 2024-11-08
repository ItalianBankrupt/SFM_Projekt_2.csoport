package com.spa.demo.frontend.Cassa;

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

public class IdHandler {

    @FXML
    private Button AddId;
    private Label AmountToPay;
    private Buyer buyer;
    private List<String> Ids = new ArrayList<>();
    private String newID = "";
    private String RemoveId = "";

    @FXML
    private ListView<String> ListOfIds;

    @FXML
    void AddId(ActionEvent event) {
        newID = buyer.GenerateId();
        Ids.add(newID);
        ListOfIds.getItems().add(newID);
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
        Ids.remove(RemoveId);
        buyer.setNumberOfGeneratedId(buyer.getNumberOfGeneratedId()- 1);
        ListOfIds.getItems().remove(RemoveId);
    }

    @FXML
    void changeBuyerInfos(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CassaGUI/AddCustomer.fxml"));
        Parent root = loader.load();
        AddCustomer addCustomer = loader.getController();
        addCustomer.SendBuyerToAddCustomer(buyer);

        Stage stage = new Stage();
        stage.setTitle("Id Handler");
        stage.setScene(new Scene(root));
        stage.show();

        //close this window
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }


    public void sendBuyerInfos(Buyer buyer)
    {
        this.buyer = buyer;
    }

    public void initialization()
    {
        AddId.fire();
    }
}
