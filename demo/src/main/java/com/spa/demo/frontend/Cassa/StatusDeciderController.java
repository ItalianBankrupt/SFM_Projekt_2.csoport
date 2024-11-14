package com.spa.demo.frontend.Cassa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class StatusDeciderController {

    @FXML
    private ToggleGroup StatusInfo;

    @FXML
    private RadioButton radioButtonAdult;

    @FXML
    private RadioButton radioButtonRetired;

    @FXML
    private RadioButton radioButtonStudent;

    @FXML
    void sendBackStatus(ActionEvent event) throws IOException {

        if(radioButtonAdult.isSelected()) {
            Status = 1;
        }
        if(radioButtonStudent.isSelected()) {
            Status = 2;
        }
        if(radioButtonRetired.isSelected()) {
            Status = 3;
        }


        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }

    public int Status;


}
