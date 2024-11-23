package com.spa.demo.frontend.Cassa.Utils;

import javafx.scene.control.Alert;

public class PopUpWindows {
    public static void AlertWindow(String contentText, String title, String headerText)
    {
        Alert missingData = new Alert(Alert.AlertType.ERROR);
        missingData.setContentText(contentText);
        missingData.setHeaderText(headerText);
        missingData.setTitle(title);
        missingData.showAndWait();
    }
}
