package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.CupboardRepository;
import com.spa.demo.backend.IdentificationRepository;
import com.spa.demo.backend.RegistrationRepository;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class UploadBalance {

    private Node node;
    private ConfigurableApplicationContext context;
    private RegistrationRepository registrationRepository;
    private IdentificationRepository identificationRepository;
    private CupboardRepository cupboardRepository;
    @FXML
    private TextField balanceToUpload;

    @FXML
    private TextField customerId;

    @FXML
    void uploadBalance(ActionEvent event) {
        context = SpringManager.getApplicationContext();
        registrationRepository = context.getBean(RegistrationRepository.class);
        identificationRepository = context.getBean(IdentificationRepository.class);
        cupboardRepository = context.getBean(CupboardRepository.class);
    }


    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    public void initialize()
    {

    }


}
