package org.example.Cassa;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.example.Index;

import java.io.*;

import java.io.IOException;

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
    void generateID(ActionEvent event) {

        String name = customer_name.getText();
        String postcode = customer_post_code.getText();
        String city = customer_city.getText();
        String street = customer_street.getText();
        String id = customer_id.getText();
        Buyer buyer = new Buyer(id,name,city,street,postcode);




        String correctInfoStatus = checkInfos(buyer.getPostCode(), buyer.getId());

        if(!correctInfoStatus.equals("none")){
            switch(correctInfoStatus)
            {
                case "postcode":
                    customer_post_code.setStyle("-fx-background-color: red;");
                    break;
                case "ID":
                    customer_id.setStyle("-fx-background-color: red;");
                    break;
            }
            WrongInputAlert(correctInfoStatus);
        }
    }

    private  void MissingInputAlert()
    {
        Alert missingData = new Alert(Alert.AlertType.ERROR);
        missingData.setContentText("Valamelyik adat hiányos!");
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
        else if (ID.length() != 11)
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

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Index.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

}
