package org.example.Cassa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Index;

import java.io.IOException;

public class MainPage {

    @FXML
    void checkId(ActionEvent event) throws IOException {
        openScene("/org/example/CassaGUI/checkID.fxml", "Check ID");
    }

    @FXML
    void newCustomer(ActionEvent event) throws IOException {
        openScene("/org/example/CassaGUI/addCustomer.fxml", "New customer");
    }

    @FXML
    void RemoveCustomer(ActionEvent event) throws IOException{
        openScene("/org/example/CassaGUI/removeCustomer.fxml", "Remove Customer");
    }

    @FXML
    void uploadBalance(ActionEvent event) throws IOException{
        openScene("/org/example/CassaGui/uploadBalance.fxml", "Upload balance");
    }

    private void openScene(String url, String title) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource(url));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
