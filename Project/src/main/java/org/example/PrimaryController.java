package org.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.*;
import javafx.fxml.FXML;
import org.h2.tools.Server;

import org.example.*;

public class PrimaryController {
    private static Scene scene;
    @FXML
    public static Scene switchToSecondary() throws IOException, SQLException {
        Scene scene = new Scene(loadFXML("CassaGUI/MainPage"), 640, 480);
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
