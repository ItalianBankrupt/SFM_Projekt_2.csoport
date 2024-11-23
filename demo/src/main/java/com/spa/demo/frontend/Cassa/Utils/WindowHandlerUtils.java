package com.spa.demo.frontend.Cassa.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class WindowHandlerUtils
{
    private static Parent root;
    private static Stage stage;
    private static Node node;

    //Visszatérési értekéül egy FXMLLoader-t add ami rá van állítva a paraméterként megadott fxml-re
    //illet a Parent tipusú root-ba betölti az FXML dokumentumot
    public static FXMLLoader getFXMLoader(String fxmlPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(WindowHandlerUtils.class.getResource(fxmlPath));
        root = loader.load();
        return loader;
    }

    //paraméterként megkell adni egy FXMLLoader tipusú objectet és egy String tipusú Title-t ami az új scene Title-je lesz
    //A metodús megnyitja az új scene-t, de nem zárja be a korábbit
    public static void OpenScene(FXMLLoader fxmlLoader, String Title) throws IOException {
        stage = new Stage();
        stage.setTitle(Title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Ha paraméterként az FXMLLoader és title mellett kap egy Node paramétert is, akkor a metodus bezárja az adott ablakot
    public static void OpenScene(FXMLLoader fxmlLoader, String Title, Node node) throws IOException {
        stage = new Stage();
        stage.setTitle(Title);
        stage.setScene(new Scene(root));
        stage.show();
        CloseScene(node);
    }


    //paraméternek meg kell adni egy Node változot, és a metodus bezárja az adott ablakot
    public static void CloseScene(Node node) {
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }

    public static void BackToCassaMainPage(Node node) throws IOException {
        FXMLLoader loader = getFXMLoader("/fxml/CassaGUI/MainPage.fxml");
        OpenScene(loader, "CassaGUI", node);
    }

}
