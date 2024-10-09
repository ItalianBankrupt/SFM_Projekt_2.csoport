package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

import org.example.Restaurant.JpaRestaurantDAO;
import org.example.Restaurant.RestaurantUtils;
import org.h2.tools.Server;
import java.io.IOException;


import org.example.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
        try {
            Scene scene1 = PrimaryController.switchToSecondary();
            stage.setScene(scene1);
            stage.show();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException
    {
        StartDatabase();
        launch();
        RestaurantUtils restaurantUtils = new RestaurantUtils();
        restaurantUtils.setrDAO(new JpaRestaurantDAO());
        restaurantUtils.runUtils();
        StopDatabase();




        System.out.println("Open your browser and navigate to http://localhost:8082/");
        System.out.println("JDBC URL: jdbc:h2:file:~/Project");
        System.out.println("User Name: sa");
        System.out.println("Password: ");

    }

    private static void StartDatabase() throws SQLException{
        new Server().runTool("-tcp","-web","-ifNotExists");
    }
    private static void StopDatabase() throws SQLException{
        new Server().shutdown();
    }
}