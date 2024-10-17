package org.example;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.Restaurant.JpaRestaurantDAO;
import org.example.Restaurant.Restaurant;
import org.example.Restaurant.RestaurantDAO;
import org.example.Restaurant.RestaurantUtils;
import org.example.Services.JpaServicesDAO;
import org.example.Services.ServicesUtils;
import org.h2.tools.Server;

import java.io.IOException;
import java.sql.SQLException;


public class Index extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Főoldal");
        stage.setScene(scene);
        stage.show();

        startDatabase();
        RestaurantUtils restaurantUtils = new RestaurantUtils();
        restaurantUtils.setrDAO(new JpaRestaurantDAO());
        restaurantUtils.runUtils();

        ServicesUtils servicesUtils = new ServicesUtils();
        servicesUtils.setrDAO(new JpaServicesDAO());
        servicesUtils.runUtils();

        System.out.println("Open your browser and navigate to http://localhost:8082/");
        System.out.println("JDBC URL: jdbc:h2:file:~/Project");
        System.out.println("User Name: sa");
        System.out.println("Password: ");
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
        
    }

    private static void startDatabase() throws SQLException{
        new Server().runTool("-tcp","-web","-ifNotExists");
    }

}

