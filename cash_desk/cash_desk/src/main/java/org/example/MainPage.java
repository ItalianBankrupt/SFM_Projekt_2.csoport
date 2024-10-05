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
    void newCustomer(ActionEvent event) {

    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


}
