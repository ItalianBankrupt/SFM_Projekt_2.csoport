package com.spa.demo.frontend.Cassa;
import com.spa.demo.SpringManager;
import com.spa.demo.backend.Identification;
import com.spa.demo.backend.IdentificationRepository;
import com.spa.demo.frontend.Cassa.Utils.JavaFxElementModifier;
import com.spa.demo.frontend.Cassa.Utils.PopUpWindows;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.context.ConfigurableApplicationContext;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadBalance {

    private Node node;

    private ObservableList<String> ids = FXCollections.observableArrayList();
    private ConfigurableApplicationContext context;
    private IdentificationRepository identificationRepository;

    @FXML
    private ListView<String> ListView_ids;

    @FXML
    private TextField TextField_Balance;

    @FXML
    private TextField TextField_Id;

    @FXML
    void uploadBalance(ActionEvent event) {
        JavaFxElementModifier.changeTextFieldBackgroundColor("white", TextField_Balance,TextField_Id);
        if(TextField_Balance.getText().isEmpty() || TextField_Id.getText().isEmpty()) {
            String contentText = "Hiányzik a feltöltendő egyenleg vagy az azonosító";
            String headerText = "Hiányos adat";
            String title = "Hiba";
            PopUpWindows.AlertWindow(contentText, headerText, title);
            if(TextField_Balance.getText().isEmpty()){
                JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_Balance);
            }
            if(TextField_Id.getText().isEmpty()){
                JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_Id);
            }
        }
        else{
            Identification searchedIdentification = new Identification();
            List<Identification> identifications = identificationRepository.findAll();
            for (Identification identification : identifications) {
                if(identification.getPersonId().equals(TextField_Id.getText())){
                    searchedIdentification = identification;
                }
            }
            searchedIdentification.setMoney(Integer.parseInt(TextField_Balance.getText()) + searchedIdentification.getMoney());
            identificationRepository.updateByPersonId(searchedIdentification.getPersonId(), searchedIdentification.getMoney());
            String contentText = "Sikeres módosítás!\nA " + searchedIdentification.getPersonId() + "-hoz tartozó új egyenleg: "+ searchedIdentification.getMoney();
            String headerText = "Sikeres feltöltés";
            String title = "Sikeres";
            PopUpWindows.InfoWindow(contentText, headerText, title);
        }

    }


    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    private void filter()
    {
        String typedString = TextField_Id.getText();
        ObservableList<String> filteredIds = FXCollections.observableArrayList();
        for (String id : ids) {
            if(id.contains(typedString))
            {
                filteredIds.add(id);
            }
        }
        ListView_ids.setItems(filteredIds);

    }

    public void initialize()
    {
        context = SpringManager.getApplicationContext();
        identificationRepository = context.getBean(IdentificationRepository.class);
        List<Identification> quaryId = identificationRepository.findAll().stream().toList();
        for (Identification id : quaryId) {
            ids.add(id.getPersonId());
        }
        ListView_ids.setItems(ids);

        TextField_Balance.textProperty().addListener((observable, oldValue, newValue) -> {
            filter();
            if(newValue.length() > oldValue.length())
            {
                if(Character.isDigit(newValue.charAt(newValue.length() - 1))) {
                    TextField_Balance.setText(newValue);
                }
                else{
                    TextField_Balance.setText(oldValue);
                }
            }


        });

        ListView_ids.setOnMouseClicked(event -> {
            TextField_Id.setText(ListView_ids.getSelectionModel().getSelectedItem());
        });
    }


}
