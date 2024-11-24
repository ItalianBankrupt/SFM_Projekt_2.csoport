package com.spa.demo.frontend.Cassa;

import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckID {

    private Node node;
    @FXML
    private TextField watchId;

    @FXML
    void getWatchInformations(ActionEvent event) {

    }

    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    public void initialize()
    {

    }


}

