package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Identification;
import com.spa.demo.backend.IdentificationRepository;
import com.spa.demo.frontend.Cassa.Models.PersonId;
import com.spa.demo.frontend.Cassa.Utils.PopUpWindows;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CheckID {

    private Node node;
    private IdentificationRepository identificationRepository;
    private ObservableList<String> ids = FXCollections.observableArrayList();
    List<Identification> quaryList = new ArrayList<>();

    @FXML
    private ListView<String> ListView_ID;

    @FXML
    private TextArea TextArea_Info;

    @FXML
    private TextField TextField_ID;

    @FXML
    void QuaryInfos(ActionEvent event) {
        TextArea_Info.clear();

        if(TextField_ID.getText().isEmpty()) {
            String contentText = "Nincs azonosító beírva a kereséshez";
            String headerText = "Azonosító hiba";
            String title = "Hiba";
            PopUpWindows.AlertWindow(contentText, headerText, title);
        }

        PersonId personId = null;

        for (Identification id : quaryList) {
            if(Objects.equals(id.getPersonId(), TextField_ID.getText())) {
                personId = new PersonId(id);
            }
        }

        List<String> ticketInfos = new ArrayList<>();
        List<String> servicesInfos = new ArrayList<>();

        if(personId != null) {
            ticketInfos = personId.listTicketsWithoutZeroValues();
            servicesInfos = personId.listServicesInfo();

            StringBuilder information = new StringBuilder("Jegyek:\n");
            for (String ticket : ticketInfos) {
                information.append(ticket).append("\n");
            }
            if(!servicesInfos.isEmpty()) {
                information.append("Szolgáltatások:\n");
                for (String service : servicesInfos) {
                    information.append(service).append("\n");
                }
            }

            information.append("Egyenleg: " + personId.getBalance().getValue() );
            TextArea_Info.setText(information.toString());
        }
        else
        {
            String contentText = "Nem található ilyen azonosító!";
            String headerText = "Hibás azonosító";
            String title = "Hiba";
            PopUpWindows.AlertWindow(contentText, headerText, title);
        }
    }

    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    private void filter()
    {
        String typedString = TextField_ID.getText();
        ObservableList<String> filteredIds = FXCollections.observableArrayList();
        for (String id : ids) {
            if(id.contains(typedString))
            {
                filteredIds.add(id);
            }
        }
        ListView_ID.setItems(filteredIds);

    }

    public void initialize()
    {
        TextArea_Info.setEditable(false);
        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        identificationRepository = context.getBean(IdentificationRepository.class);
        quaryList = identificationRepository.findAll();

        for (Identification id : quaryList) {
            ids.add(id.getPersonId());
        }
        ListView_ID.setItems(ids);

        ListView_ID.setOnMouseClicked(event -> {
            TextField_ID.setText(ListView_ID.getSelectionModel().getSelectedItem());
        });

        TextField_ID.textProperty().addListener((observable, oldValue, newValue) -> {
            filter();
        });

    }
}

