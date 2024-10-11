package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexController {
    @FXML
    public void openCassaMainPageGUI(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cassa/MainPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("CassaGUI");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void openRestaurantMainPageGUI(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cassa/MainPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("RestaurantGUI");
        stage.setScene(new Scene(root));
        stage.show();

    }


}
