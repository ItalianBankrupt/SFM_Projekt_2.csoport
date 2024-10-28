package org.example.Cassa;

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
import org.example.Index;

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
            setBgToRed(customer_name);
            missingInputs.add("Név");
            emptyField = true;
        }
        if(postcode.isEmpty())
        {
            setBgToRed(customer_post_code);
            missingInputs.add("Irányítószám");
            emptyField = true;
        }
        if(city.isEmpty())
        {
            setBgToRed(customer_city);
            missingInputs.add("Település");
            emptyField = true;
        }
        if(street.isEmpty())
        {
            setBgToRed(customer_street);
            missingInputs.add("Utca,házszám");
            emptyField = true;
        }
        if(id.isEmpty())
        {
            setBgToRed(customer_id);
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
                    setBgToRed(customer_post_code);
                    break;
                case "ID":
                    setBgToRed(customer_id);
                    break;
            }
            WrongInputAlert(correctInfoStatus);
        }
        else
        {
            Stage current = (Stage)((Node) event.getSource()).getScene().getWindow();
            Scene IdHandler = new Scene(loadFXML("/org/example/CassaGUI/IdHandler"));
            Stage prompt = new Stage();
            prompt.setTitle("Id Handler");
            prompt.setScene(IdHandler);
            current.close();
            prompt.show();
        }
    }


    private void setAllTfToWhite(TextField customerName, TextField customerId, TextField customerPostCode, TextField customerStreet, TextField customerCity)
    {
        setBgToWhite(customerName);
        setBgToWhite(customerId);
        setBgToWhite(customerPostCode);
        setBgToWhite(customerStreet);
        setBgToWhite(customerCity);
    }

    private void setBgToWhite(TextField tf)
    {
        tf.setStyle("-fx-background-color: white;");
    }

    //sets textfield background to red
    private void setBgToRed(TextField tf)
    {
        tf.setStyle("-fx-background-color: red;");
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
