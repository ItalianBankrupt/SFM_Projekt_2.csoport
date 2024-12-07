package com.spa.demo.frontend;


import com.spa.demo.SpringManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {
    static Manager manager= new SpringManager();

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Customer handler");
        stage.setOnCloseRequest(event -> {
            try {
                stop(); //
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        manager.startBackend();
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        manager.stopBackend();
        Platform.exit();
        super.stop();
    }
}
