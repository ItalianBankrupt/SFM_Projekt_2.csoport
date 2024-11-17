package com.spa.demo.frontend;

import com.spa.demo.SpaApplication;
import com.spa.demo.SpringManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class IndexController {

    private Main main;

    @FXML
    public void openCassaMainPageGUI(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/CassaGUI/MainPage.fxml"));
       // fxmlLoader.setControllerFactory(SpringManager.getApplicationContext()::getBean);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("CassaGUI");
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void openRestaurantMainPageGUI(ActionEvent actionEvent) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/RestGUI/identification.fxml"));

        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("RestaurantGUI");
        stage.setScene(new Scene(root));
        stage.show();

    }

}
