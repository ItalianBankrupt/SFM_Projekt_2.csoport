package com.spa.demo.frontend.Cassa.addcustomerControllers;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import com.spa.demo.frontend.Cassa.Models.PersonId;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class TicketsAddController {

    public PersonId personId;
    public int balance = 0;
    private ServicesRepository servicesRepository;
    @FXML
    private ListView<String> ListOfTickets;

    @FXML
    private Button functionButton;

    @FXML
    private Label Label_ID;

    //Gombnyomására megnézi, hogy a gomb az Hozzáad vagy Töröl
    //Ha hozzáad akkor a personId példányba a kiválasztott jegy típust 1-re állítja, lekérdezi az árát és a balance-ot növeli
    //HA töröl akkor a personId példányba a kiválasztott jegy típust 0-ra állítja, lekérdezi az árát és a balance-ot csökkenti
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

    //Gombnyomásra bezárja az ablakot
    @FXML
    void Finish(ActionEvent event) {
        Node node = (Node) event.getSource();
        WindowHandlerUtils.CloseScene(node);
    }

    //Publikus metodus segitségével a TicketsAndServicesControllertől megkapja a personId példányt
    public void getPersonIdFromTicketAndServices(PersonId AnotherpersonId)
    {
        personId = AnotherpersonId;
        init();
    }

    //Paraméterül kapott érték alapján modosítja a functionButton-t
    //Ha 0 akkor Hozzáadd-ra ha 1 akkor Töröl-re
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


    //A paraméterül adott String alapján meghatározza a jegyek típusát
    //1-es add vissza FE-re azaz felnőtt
    //2-est add vissza a DI-re azaz diák
    //3-ast add vissza a NY-re azaz nyugdíjasra
    //Ellenkező esetben 0-át add vissza
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

    //Paraméterként megadott jegytipushoz tartozó darabszámot adja vissza a PersonId példányban
    //Ha nincs ilyen jegytipus akkor -1-et add vissza, amúgy meg van 1-et vagy 0-át
    private int QuaryTicketStatus(String ticketType)
    {
        return switch (ticketType) {
            case "Felnőtt élményfürdő belépő" -> personId.getAdultFellingTicket();
            case "Diák élményfürdő belépő" -> personId.getStudentFellingTicket();
            case "Nyugdijas élményfürdő belépő" -> personId.getFeelingPensionerTicket();
            case "Diák belépő" -> personId.getStudentBeachTicket();
            case "Felnőtt belépő" -> personId.getAdultBeachTicket();
            case "Nyugdíjas belépő" -> personId.getPensionerBeachTicket();
            case "Diák Thermal belépő" -> personId.getStudentThermalTicket();
            case "Felnőtt Thermal belépő" -> personId.getAdultThermalTicket();
            case "Nyugdíjas Thermal belépő" -> personId.getPensionerThermalTicket();
            case "Aquapark belépő" -> personId.getAquaParkTicket();
            case "Prémium belépőjegy" -> personId.getPremiumTicket();
            default -> -1;
        };
    }

    //Első paraméter a jegytipus amit a personId példány megfelelő attributumához tartozó értéket modosítja a második paraméter alapján
    //Második paraméter vagy 1 vagy 0
    private void SetPersonIdStatus(String ticketType, int status)
    {
        switch (ticketType){
            case "Felnőtt élményfürdő belépő":
                personId.setAdultFellingTicket(status);
                break;
            case "Diák élményfürdő belépő":
                personId.setStudentFellingTicket(status);
                break;
            case "Nyugdijas élményfürdő belépő":
                personId.setFeelingPensionerTicket(status);
                break;
            case "Diák belépő":
                personId.setStudentBeachTicket(status);
                break;
            case "Felnőtt belépő":
                personId.setAdultBeachTicket(status);
                break;
            case "Nyugdíjas belépő":
                personId.setPensionerBeachTicket(status);
                break;
            case "Diák Thermal belépő":
                personId.setStudentThermalTicket(status);
                break;
            case "Felnőtt Thermal belépő":
                personId.setAdultThermalTicket(status);
                break;
            case "Nyugdíjas Thermal belépő":
                personId.setPensionerThermalTicket((status));
                break;
            case "Aquapark belépő":
                personId.setAquaParkTicket(status);
                break;
            case "Prémium belépőjegy":
                personId.setPremiumTicket(status);
                break;
        }
    }

    //Paraméterként megkapott jegyhez tartozó árat kéri le a servicesRepositoryból
    //visszatérési értéke az ár, ha nincs olyen jegytipus akkor 0-val tér vissza
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

    //Labelök és button-ok beállítása, Listview feltöltése
    private void init()
    {
        Label_ID.setText(personId.getId());

        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        servicesRepository = context.getBean(ServicesRepository.class);

        //Felnőtt, gyerek, nyugdijas meghatározása
        String idStatus = personId.getId().substring(0,2);
        int ticketType = identifyTicketType(idStatus);
        //Megfelelő jegyek listázása
        List<Services> listOfEveryTicket = servicesRepository.findByType("Belepo").stream().toList();
        List<String> ListOfTicketsByTicketType = new ArrayList<>();
        for (Services service : listOfEveryTicket) {
            if(service.getTicketType() == ticketType || service.getTicketType() == 0 ){
                ListOfTicketsByTicketType.add(service.getName());
            }
        }
        //Listview-ba értékek feltöltése, első kiválasztottra tétele
        ListOfTickets.setItems(FXCollections.observableList(ListOfTicketsByTicketType));
        ListOfTickets.getSelectionModel().select(0);
        ButtonTextStatus(QuaryTicketStatus(ListOfTickets.getSelectionModel().getSelectedItem()));

        //Gombnyomásra változzon meg a jegyhez tartozó megfelelő darabszám
        ListOfTickets.setOnMouseClicked(mouseEvent -> {
            int stat = QuaryTicketStatus(ListOfTickets.getSelectionModel().getSelectedItem());
            ButtonTextStatus(stat);
        });

    }

}
