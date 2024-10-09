package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPage {

    @FXML
    void checkId(ActionEvent event) throws IOException {
        Scene idChecker = new Scene(loadFXML("checkID"));
        Stage prompt = new Stage();
        prompt.setTitle("Check ID");
        prompt.setScene(idChecker);
        prompt.show();
    }

    @FXML
    void newCustomer(ActionEvent event) throws IOException {
        Scene addNewCustomer = new Scene(loadFXML("addCustomer"));
        Stage prompt = new Stage();
        prompt.setTitle("New customer");
        prompt.setScene(addNewCustomer);
        prompt.show();
    }

    @FXML
    void RemoveCustomer(ActionEvent event) throws IOException{
        Scene removeCustomer = new Scene(loadFXML("removeCustomer"));
        Stage prompt = new Stage();
        prompt.setTitle("Remove Customer");
        prompt.setScene(removeCustomer);
        prompt.show();
    }

    @FXML
    void uploadBalance(ActionEvent event) throws IOException{
        Scene uploadBalance = new Scene(loadFXML("uploadBalance"));
        Stage prompt = new Stage();
        prompt.setTitle("Upload balance");
        prompt.setScene(uploadBalance);
        prompt.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


}
