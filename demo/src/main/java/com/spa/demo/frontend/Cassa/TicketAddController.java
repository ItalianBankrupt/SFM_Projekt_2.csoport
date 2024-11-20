package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TicketAddController {

    public PersonId personId;
    public int balance = 0;
    private ServicesRepository servicesRepository;
    @FXML
    private ListView<String> ListOfTickets;

    @FXML
    private Button functionButton;

    @FXML
    private Label idLabel;

    @FXML
    void AddOrDelete(ActionEvent event) {
        if(functionButton.getText().equals("Hozzáadd")) {
            SetPersonIdStatus(ListOfTickets.getSelectionModel().getSelectedItem(), 1);
            int thisTicketPrice = QuaryTicketPrice(ListOfTickets.getSelectionModel().getSelectedItem());
            balance += thisTicketPrice;
            ButtonTextStatus(1);
        }
        else if(functionButton.getText().equals("Töröl"))
        {
            SetPersonIdStatus(ListOfTickets.getSelectionModel().getSelectedItem(), 0);
            int thisTicketPrice = QuaryTicketPrice(ListOfTickets.getSelectionModel().getSelectedItem());
            balance -= thisTicketPrice;
            ButtonTextStatus(0);
        }
    }

    @FXML
    void Finish(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }

    public void getPersonIdFromTicketAndServices(PersonId AnotherpersonId)
    {
        personId = AnotherpersonId;
        init();
    }

    private void ButtonTextStatus(int Status)
    {
        if(Status == 0)
        {
            functionButton.setText("Hozzáadd");
        }
        else if(Status == 1){
            functionButton.setText("Töröl");
        }
    }

    private void init()
    {
        idLabel.setText(personId.getId());
        SpringManager springManager = new SpringManager();
        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        servicesRepository = context.getBean(ServicesRepository.class);
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
        ListOfTickets.getSelectionModel().select(0);
        ButtonTextStatus(QuaryTicketStatus(ListOfTickets.getSelectionModel().getSelectedItem()));

        ListOfTickets.setOnMouseClicked(mouseEvent -> {
            int stat = QuaryTicketStatus(ListOfTickets.getSelectionModel().getSelectedItem());
            ButtonTextStatus(stat);
        });

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

    private int QuaryTicketStatus(String ticketType)
    {
        return switch (ticketType) {
            case "Felnőtt élményfürdő belépő" -> personId.getAdultFelling();
            case "Diák élményfürdő belépő" -> personId.getStudentFelling();
            case "Nyugdijas élményfürdő belépő" -> personId.getRetiredFelling();
            case "Diák belépő" -> personId.getStudentBeachTicket();
            case "Felnőtt belépő" -> personId.getAdultBeachTicket();
            case "Nyugdíjas belépő" -> personId.getRetiredBeachTicket();
            case "Diák Thermal belépő" -> personId.getStudentThermalTicket();
            case "Felnőtt Thermal belépő" -> personId.getAdultThermalTicket();
            case "Nyugdíjas Thermal belépő" -> personId.getRetiredThermalTicket();
            case "Aquapark belépő" -> personId.getAquaParkTicket();
            case "Prémium belépőjegy" -> personId.getPremiumTicket();
            default -> -1;
        };
    }

    private void SetPersonIdStatus(String ticketType, int status)
    {
        switch (ticketType){
            case "Felnőtt élményfürdő belépő":
                personId.setAdultFelling(status);
                break;
            case "Diák élményfürdő belépő":
                personId.setStudentFelling(status);
                break;
            case "Nyugdijas élményfürdő belépő":
                personId.setRetiredFelling(status);
                break;
            case "Diák belépő":
                personId.setStudentBeachTicket(status);
                break;
            case "Felnőtt belépő":
                personId.setAdultBeachTicket(status);
                break;
            case "Nyugdíjas belépő":
                personId.setRetiredBeachTicket(status);
                break;
            case "Diák Thermal belépő":
                personId.setStudentThermalTicket(status);
                break;
            case "Felnőtt Thermal belépő":
                personId.setAdultThermalTicket(status);
                break;
            case "Nyugdíjas Thermal belépő":
                personId.setRetiredThermalTicket((status));
                break;
            case "Aquapark belépő":
                personId.setAquaParkTicket(status);
                break;
            case "Prémium belépőjegy":
                personId.setPremiumTicket(status);
                break;
        }
    }

    private int QuaryTicketPrice(String ticketName)
    {
        List<Services> listOfEveryTicket = servicesRepository.findByType("Belepo").stream().toList();
        for (Services services:listOfEveryTicket)
        {
            if(services.getName().equals(ticketName))
            {
                return services.getPrice();
            }
        }
        return 0;
    }
}
