package org.example;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXML;
import org.h2.tools.Server;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException, SQLException {
        App.setRoot("secondary");
    }
}
