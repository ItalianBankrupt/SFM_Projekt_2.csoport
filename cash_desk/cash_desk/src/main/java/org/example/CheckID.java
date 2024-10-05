package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CheckID {

    @FXML
    private TextField watchId;

    @FXML
    void getWatchInformations(ActionEvent event) {
        Stage stage = (Stage) watchId.getScene().getWindow();
        stage.close();
    }

}

