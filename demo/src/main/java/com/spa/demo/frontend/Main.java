package com.spa.demo.frontend;


import com.spa.demo.Spring.SpringManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    static Manager manager= new SpringManager();

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        stage.setScene(new Scene(root));

        manager.startBackend();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        manager.stopBackend();
        super.stop();
    }
}
