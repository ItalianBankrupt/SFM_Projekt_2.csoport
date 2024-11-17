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
            ButtonTextStatus(1);
        }
        else if(functionButton.getText().equals("Töröl"))
        {
            SetPersonIdStatus(ListOfTickets.getSelectionModel().getSelectedItem(), 0);
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
        ListOfTickets.getSelectionModel().select(0);
        ButtonTextStatus(QuaryPersonIdStatus(ListOfTickets.getSelectionModel().getSelectedItem()));

        ListOfTickets.setOnMouseClicked(mouseEvent -> {
            int stat = QuaryPersonIdStatus(ListOfTickets.getSelectionModel().getSelectedItem());
            ButtonTextStatus(stat);
            System.out.println(personId.toString());
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

    private int QuaryPersonIdStatus(String ticketType)
    {
        switch (ticketType){
            case "Felnőtt élményfürdő belépő":
                    return personId.getAdultFelling();
            case "Diák élményfürdő belépő":
                    return personId.getStudentFelling();
            case "Gyermek belépő":
                    return personId.getChildTicket();
            case "Fürdő belépő":
                    return personId.getBeachTicket();
            case "Nyugdíjas belépő":
                    return personId.getRetiredTicket();
            case "Diák/Gyermek belépő":
                    return personId.getStudendAndChildTicket();
            case "Aquapark normal belépő":
                    return personId.getAquaParkNormal();
            case "Aquapark minimal belépő":
                    return personId.getAquaParkMinimal();
            case "Prémium belépőjegy":
                    return personId.getPremiumTicket();
            default: return -1;
        }
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
            case "Gyermek belépő":
                 personId.setChildTicket(status);
                 break;
            case "Fürdő belépő":
                 personId.setBeachTicket(status);
                 break;
            case "Nyugdíjas belépő":
                 personId.setRetiredTicket(status);
                 break;
            case "Diák/Gyermek belépő":
                 personId.setStudendAndChildTicket(status);
                 break;
            case "Aquapark normal belépő":
                 personId.setAquaParkNormal(status);
                 break;
            case "Aquapark minimal belépő":
                 personId.setAquaParkMinimal(status);
                 break;
            case "Prémium belépőjegy":
                 personId.setPremiumTicket(status);
                 break;
        }
    }

}
