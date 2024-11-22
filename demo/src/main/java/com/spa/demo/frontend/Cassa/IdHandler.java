package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Registration;
import com.spa.demo.backend.RegistrationRepository;
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
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observer;

@Controller
public class IdHandler {

    public RegistrationRepository registrationRepository;

    @FXML
    private Button AddId;
    private Buyer buyer;

    @FXML
    private ListView<String> ListOfIds;

    @FXML
    private ToggleGroup StatusInfo;

    @FXML
    private RadioButton AdultRadioButton;

    @FXML
    private RadioButton RetiredRadioButton;

    @FXML
    private RadioButton StudentRadioButton;

    @FXML
    void AddId(ActionEvent event) throws IOException {
        if (StatusInfo.getSelectedToggle() == null)
        {
            changeBgColorRadioButtons("red");
            Alert WrongInput = new Alert(Alert.AlertType.ERROR);
            WrongInput.setContentText("Válassza a jegy típusát:Diák/felnőtt/nyugdíjas");
            WrongInput.setHeaderText("Nincs kiválasztott típus");
            WrongInput.setTitle("Hiba");
            WrongInput.showAndWait();
        }
        else{
            changeBgColorRadioButtons("#d5c990");
            int status = -1;
            if(StudentRadioButton.isSelected())
            {
                status = 2;
                StudentRadioButton.setSelected(false);
            }
            else if(AdultRadioButton.isSelected())
            {
                status = 1;
                AdultRadioButton.setSelected(false);
            }
            else if(RetiredRadioButton.isSelected())
            {
                status = 3;
                RetiredRadioButton.setSelected(false);
            }
            buyer.GenerateId(status);

        }
    }

    private void changeBgColorRadioButtons(String color){
        AdultRadioButton.setStyle("-fx-background-color:" +color + ";");
        RetiredRadioButton.setStyle("-fx-background-color:" +color + ";");
        StudentRadioButton.setStyle("-fx-background-color:" +color + ";");
    }

    public void GenID(int Status)
    {
        buyer.GenerateId(Status);
    }

    @FXML
    void RemoveId(ActionEvent event) {
        if(Objects.equals(ListOfIds.getSelectionModel().getSelectedItem(), buyer.getIds().get(0)))
        {
            Alert WrongInput = new Alert(Alert.AlertType.ERROR);
            WrongInput.setContentText("Az első azonosító nem törölhető, módosítsd a vevő adatait!");
            WrongInput.setHeaderText("Nem törölhető");
            WrongInput.setTitle("Hiba");
            WrongInput.showAndWait();
            return;
        }
        String removeId = ListOfIds.getSelectionModel().getSelectedItem();
        buyer.RemoveId(removeId);
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

    @FXML
    void GoToTicketsAndServices(ActionEvent event) throws IOException {
        if(buyer.getIds().isEmpty())
        {
            Alert WrongInput = new Alert(Alert.AlertType.ERROR);
            WrongInput.setContentText("Nincsenek legenerált ID-k");
            WrongInput.setHeaderText("Nincs ID!");
            WrongInput.setTitle("Hiba");
            WrongInput.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CassaGUI/TicketsAndServices.fxml"));
        Parent root = loader.load();
        TicketAndServicesController ticketAndServicesController = loader.getController();
        ticketAndServicesController.receiveBuyer(buyer);

        Stage stage = new Stage();
        stage.setTitle("Tickets and Services");
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
        if(buyer.getIds().isEmpty()) {
            AddId.fire();
        }
        ListOfIds.setItems(buyer.getIds());
    }
}
