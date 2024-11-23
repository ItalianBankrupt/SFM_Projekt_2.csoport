package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.CupboardRepository;
import com.spa.demo.backend.Identification;
import com.spa.demo.backend.IdentificationRepository;
import com.spa.demo.backend.RegistrationRepository;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import jakarta.transaction.Transactional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import org.springframework.context.ConfigurableApplicationContext;


import java.io.IOException;

public class RemoveCustomer {

    private Node node;
    private ConfigurableApplicationContext context;
    private IdentificationRepository identificationRepository;

    @FXML
    private TextField customerId;

    @FXML
    void removeCustomerFromTable(ActionEvent event) {
        String customerId = this.customerId.getText();
        if(!customerId.isEmpty())
        {
           boolean success = false;
           int numbers = identificationRepository.findAll().size();
            for (int i = 0; i < numbers; i++) {
                if (identificationRepository.findAll().get(i).getPersonId().equals(customerId)) {
                    success = true;
                    break;
                }
            }
            if(success)
            {
                identificationRepository.deleteByPersonId(customerId);
                showAlert("Sikeres kiléptetés!","A vendég sikeresen kiléptetve!", AlertType.INFORMATION);
                this.customerId.clear();
            }
            else
            {
                showAlert("Sikertelen kiléptetés!","Sikertelen kiléptetés!", AlertType.ERROR);
            }
        }
    }

    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    public void initialize()
    {
        context = SpringManager.getApplicationContext();
        identificationRepository = context.getBean(IdentificationRepository.class);
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
