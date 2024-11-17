package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TicketAddController {

    private PersonId personId;
    @FXML
    private ListView<String> ListOfTickets;

    @FXML
    private Button functionButton;

    @FXML
    private Label idLabel;

    @FXML
    void AddOrDelete(ActionEvent event) {

    }

    public void getPersonIdFromTicketAndServices(PersonId AnotherpersonId)
    {
        personId = AnotherpersonId;
        init();
    }

    @FXML
    void newSelectedItem(MouseEvent event) {

    }

    private void init()
    {
        idLabel.setText(personId.getId());
        SpringManager springManager = new SpringManager();
        ConfigurableApplicationContext context = springManager.getApplicationContext();
        ServicesRepository servicesRepository = context.getBean(ServicesRepository.class);
        String idStatus = personId.getId().substring(0,2);
        int ticketType = identifyTicketType(idStatus);
        List<Services> listOfEveryTicket = servicesRepository.findByType("Belepo").stream().toList();
        List<String> ListOfTicketsByTicketType = new ArrayList<>();
        for (Services service : listOfEveryTicket) {
            if(service.getTicketType() == ticketType || service.getTicketType() == 0 ){
                ListOfTicketsByTicketType.add(service.getName());
            }
        }

        ListOfTickets.setItems(FXCollections.observableList(ListOfTicketsByTicketType));



    }

    private int identifyTicketType(String idStatus) {
        switch (idStatus){
            case "FE":
                return 1;
            case "DI":
                return 2;
            case "NY":
                return 3;
            default:
                return 0;
        }
    }
}
