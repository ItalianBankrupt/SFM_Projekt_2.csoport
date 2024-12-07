package com.spa.demo.frontend.Cassa;

import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import com.spa.demo.frontend.Cassa.addcustomerControllers.AddCustomerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class MainPageController {

    private FXMLLoader loader;
    private Node node;

    //Gomb nyomására betölti a checkId-hoz tartozó fxml-t és meghívja a initalize metodusát
    //Bezárja a MainPage scene-t is
    @FXML
    void checkId(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/checkID.fxml");
        WindowHandlerUtils.OpenScene(loader, "Id ellenőrzés",node);
    }

    //Gomb nyomására betölti a newCustomer-hoz tartozó fxml-t és meghívja a initalize metodusát
    //Bezárja a MainPage scene-t is
    @FXML
    void newCustomer(ActionEvent event) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        node = (Node) event.getSource();
        loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/addCustomerViews/addCustomer.fxml");
        WindowHandlerUtils.OpenScene(loader, "Új vásárló hozzáadása", node);
    }

    //Gomb nyomására betölti a removeCustomer-hoz tartozó fxml-t és meghívja a initalize metodusát
    //Bezárja a MainPage scene-t is
    @FXML
    void removeCustomer(ActionEvent event) throws IOException{
        node = (Node) event.getSource();
        loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/removeCustomer.fxml");
        WindowHandlerUtils.OpenScene(loader, "Vásárló kiléptetése",node);
    }

    //Gomb nyomására betölti a uploadBalance-hoz tartozó fxml-t és meghívja a initalize metodusát
    //Bezárja a MainPage scene-t is
    @FXML
    void uploadBalance(ActionEvent event) throws IOException{
        node = (Node) event.getSource();
        loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGui/uploadBalance.fxml");
        WindowHandlerUtils.OpenScene(loader, "Egyenleg feltöltése",node);
    }

}
