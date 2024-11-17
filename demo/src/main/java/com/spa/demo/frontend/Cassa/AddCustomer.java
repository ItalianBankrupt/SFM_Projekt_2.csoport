package com.spa.demo.frontend.Cassa;

import com.spa.demo.backend.Registration;
import com.spa.demo.backend.RegistrationRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@ComponentScan("com.spa.demo.backend")
public class AddCustomer {

    @FXML
    private ToggleGroup BuyerInfo;


    @FXML
    private TextField customer_city;

    @FXML
    private TextField customer_id;

    @FXML
    private TextField customer_name;

    @FXML
    private TextField customer_post_code;

    @FXML
    private TextField customer_street;

    @FXML
    private RadioButton radioButAdult;

    @FXML
    private RadioButton radioButRetired;

    @FXML
    private RadioButton radioButStudent;


    Buyer buyer = new Buyer("","","","","",0);


    public void SendBuyerToAddCustomer(Buyer sentBuyer)
    {
        customer_name.setText(sentBuyer.getName());
        customer_id.setText(sentBuyer.getId());
        customer_city.setText(sentBuyer.getCity());
        customer_street.setText(sentBuyer.getStreet());
        customer_post_code.setText(sentBuyer.getPostCode());
        if(sentBuyer.getStatus() == 1)
        {
            radioButAdult.setSelected(true);
        }
        else if(sentBuyer.getStatus() == 2)
        {
            radioButStudent.setSelected(true);
        }
        else if(sentBuyer.getStatus() == 3)
        {
            radioButRetired.setSelected(true);
        }
        this.buyer = sentBuyer;
    }

    @FXML
    void nextToIdPage(ActionEvent event) throws IOException {

        setAllTfToWhite(customer_name, customer_id, customer_post_code, customer_street, customer_city);

        String name = customer_name.getText();
        String postcode = customer_post_code.getText();
        String city = customer_city.getText();
        String street = customer_street.getText();
        String id = customer_id.getText();
        int status = 0;

        if(radioButAdult.isSelected())
        {
            status = 1;
        }
        else if(radioButRetired.isSelected())
        {
            status = 3;
        }
        else if(radioButStudent.isSelected())
        {
            status = 2;
        }

        List missingInputs = new ArrayList<String>();
        boolean emptyField = false;

        if (name.isEmpty()) {
            changeBgColor(customer_name, "red");
            missingInputs.add("Név");
            emptyField = true;
        }
        if (postcode.isEmpty()) {
            changeBgColor(customer_post_code, "red");
            missingInputs.add("Irányítószám");
            emptyField = true;
        }
        if (city.isEmpty()) {
            changeBgColor(customer_city, "red");
            missingInputs.add("Település");
            emptyField = true;
        }
        if (street.isEmpty()) {
            changeBgColor(customer_street, "red");
            missingInputs.add("Utca,házszám");
            emptyField = true;
        }
        if (id.isEmpty()) {
            changeBgColor(customer_id, "red");
            missingInputs.add("Szemlyégigazolvány szám");
            emptyField = true;
        }

        if (BuyerInfo.getSelectedToggle() == null)
        {
            missingInputs.add("diák/felnőtt/nyugdíjas");
            changeBgColorRadioButtons("red");
            emptyField = true;
        }


        if (emptyField) {
            MissingInputAlert(missingInputs);
        }

        String correctInfoStatus = checkInfos(postcode, id);

        if(!correctInfoStatus.equals("none") && !emptyField){
            switch(correctInfoStatus)
            {
                case "postcode":
                    changeBgColor(customer_post_code, "red");
                    break;
                case "ID":
                    changeBgColor(customer_id, "red");
                    break;
            }
            WrongInputAlert(correctInfoStatus);
        }
        if(correctInfoStatus.equals("none") && !emptyField)
        {
            if(!(buyer.getId().equals(id)))
            {
                buyer.setId(id);
                buyer.setNumberOfGeneratedId(0);
                buyer.UpdateList(buyer.getIds().size(), "All");
            }

            if(buyer.getStatus() != status)
            {
                buyer.setStatus(status);
                buyer.UpdateList(buyer.getIds().size(), "FirstOnly");
            }

            buyer.setName(name);
            buyer.setPostCode(postcode);
            buyer.setCity(city);
            buyer.setStreet(street);






            //open id handler with shared data
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CassaGUI/IdHandler.fxml"));
            Parent root = loader.load();
            IdHandler idHandler = loader.getController();
            idHandler.sendBuyerInfos(buyer);
            idHandler.initialization();

            Stage stage = new Stage();
            stage.setTitle("Id Handler");
            stage.setScene(new Scene(root));
            stage.show();

            //close this window
            Node node = (Node) event.getSource();
            Stage currentStage = (Stage) node.getScene().getWindow();
            currentStage.close();
        }
    }


    private void setAllTfToWhite(TextField customerName, TextField customerId, TextField customerPostCode, TextField customerStreet, TextField customerCity)
    {
        changeBgColor(customerName, "white");
        changeBgColor(customerId, "white");
        changeBgColor(customerPostCode, "white");
        changeBgColor(customerStreet, "white");
        changeBgColor(customerCity, "white");
        changeBgColorRadioButtons("white");
    }

    private void changeBgColor(TextField tf, String color)
    {
        tf.setStyle("-fx-background-color:" +color + ";");
    }

    private void changeBgColorRadioButtons(String color){
        radioButAdult.setStyle("-fx-background-color:" +color + ";");
        radioButRetired.setStyle("-fx-background-color:" +color + ";");
        radioButStudent.setStyle("-fx-background-color:" +color + ";");
    }

    private  void MissingInputAlert(List<String> list)
    {
        Alert missingData = new Alert(Alert.AlertType.ERROR);
        String missingInfo = String.join(", ", list);
        missingData.setContentText("A következő adat hiányos:"+ missingInfo);
        missingData.setHeaderText("Hiányos adatok!");
        missingData.setTitle("Hiányos vásárlói adatok");
        missingData.showAndWait();
    }

    private void WrongInputAlert(String wrongInput)
    {
        Alert WrongInput = new Alert(Alert.AlertType.ERROR);
        WrongInput.setContentText("A következő mező hibás:" + wrongInput);
        WrongInput.setHeaderText("Hibás adatok!");
        WrongInput.setTitle("Hibás vásárlói adatok");
        WrongInput.showAndWait();
    }


    //return "postcode" if the postcode's length is not 4 ,or it contains something that is not number
    //return "ID" if the id's length is not 11
    private static String checkInfos(String postCode, String ID)
    {
        if (postCode.length() != 4 || ContainsOnlyNumbers(postCode) )
            return "postcode";
        else if (ID.length() != 8)
            return "ID";
        else
            return "none";
    }

    private static boolean ContainsOnlyNumbers(String postCode)
    {
        for(int i = 0; i < postCode.length(); i++)
        {
            if(!Character.isDigit(postCode.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }
}
