package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IndexController {

    public void openCassaMainPageGUI() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cassa/MainPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("CassaGUI");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openRestaurantMainPageGUI() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cassa/MainPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("RestaurantGUI");
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void RestaurantMainPage(ActionEvent actionEvent) {

    }

    public void CassaMainPage(ActionEvent actionEvent) {
    }
}
