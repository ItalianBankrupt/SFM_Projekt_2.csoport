package com.spa.demo.frontend.Cassa;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCustomer {


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
    void nextToIdPage(ActionEvent event) throws IOException {

        setAllTfToWhite(customer_name,customer_id,customer_post_code,customer_street,customer_city);

        String name = customer_name.getText();
        String postcode = customer_post_code.getText();
        String city = customer_city.getText();
        String street = customer_street.getText();
        String id = customer_id.getText();
        Buyer buyer = new Buyer(id,name,city,street,postcode);

        List missingInputs = new ArrayList<String>();
        boolean emptyField = false;

        if(name.isEmpty())
        {
            changeBgColor(customer_name,"red");
            missingInputs.add("Név");
            emptyField = true;
        }
        if(postcode.isEmpty())
        {
            changeBgColor(customer_post_code, "red");
            missingInputs.add("Irányítószám");
            emptyField = true;
        }
        if(city.isEmpty())
        {
            changeBgColor(customer_city, "red");
            missingInputs.add("Település");
            emptyField = true;
        }
        if(street.isEmpty())
        {
            changeBgColor(customer_street,"red");
            missingInputs.add("Utca,házszám");
            emptyField = true;
        }
        if(id.isEmpty())
        {
            changeBgColor(customer_id, "red");
            missingInputs.add("Szemlyégigazolvány szám");
            emptyField = true;
        }

        if(emptyField)
        {
            MissingInputAlert(missingInputs);
        }

        String correctInfoStatus = checkInfos(buyer.getPostCode(), buyer.getId());

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
    }

    private void changeBgColor(TextField tf, String color)
    {
        tf.setStyle("-fx-background-color:" +color + ";");
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
