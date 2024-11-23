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

public class ServicesAddController {

    public PersonId personId;
    public int balance = 0;
    private ServicesRepository servicesRepository;

    @FXML
    private Label Label_Amount;

    @FXML
    private Label Label_Id;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<String> ServicesList;

    @FXML
    private Label amountLabel;

    //personId példányba a kiválasztott szolgáltatáshoz tartozó értéket eggyel növeli
    //Ha ez 0 volt akkor a deleteButton-t kittanthatóvá teszi
    //Továbbá növeli a balance-értélet és megváltoztatja a label-t
    @FXML
    void AddServices(ActionEvent event) {
        String serviceName = ServicesList.getSelectionModel().getSelectedItem();
        SetServiceStatus(serviceName, 1);
        if(deleteButton.isDisable())
        {
            deleteButton.setDisable(false);
        }
        changeAmountLabel(QuaryServiceStatus(serviceName));
        balance += QuaryServicePrice(serviceName);
    }

    //gombnyomására bezárja az ablakot
    @FXML
    void Finalize(ActionEvent event) {
        Node node = (Node) event.getSource();
        WindowHandlerUtils.CloseScene(node);
    }

    //personId példányba a kiválasztott szolgáltatáshoz tartozó értéket eggyel csökkent
    //Ha ez 0 lesz akkor a deleteButton-t megnyomását tiltja
    //Továbbá csökkent a balance-értélet és megváltoztatja a label-t
    @FXML
    void RemoveServices(ActionEvent event) {
        String serviceName = ServicesList.getSelectionModel().getSelectedItem();
        SetServiceStatus(serviceName, -1);
        if(QuaryServiceStatus(serviceName) == 0)
        {
            deleteButton.setDisable(true);
        }
        changeAmountLabel(QuaryServiceStatus(serviceName));
        balance -= QuaryServicePrice(serviceName);
    }

    //Publikus metodus segitségével a TicketsAndServicesControllertől megkapja a personId példányt
    public void getPersonIdFromTicketAndServices(PersonId AnotherpersonId) {
        personId = AnotherpersonId;
        init();
    }

    //Label értékét modosítja a paraméterként megadott értékkel
    private void changeAmountLabel(int status)
    {
        String base = "Mennyiség:";
        Label_Amount.setText(base.concat(status + ""));
    }

    //Paraméterként megadott szolgáltatás névehez tartozó personId példányba lévő értéket adja vissza
    //ha nincs ilyen érték -1-et ad vissza
    private int QuaryServiceStatus(String serviceName) {
        return switch (serviceName) {
            case "Szauna" -> personId.getSauna();
            case "Értékmegőrző" -> personId.getSafeDeposit();
            case "Pihenőágy" -> personId.getLounger();
            case "Napozóágy" -> personId.getSunBed();
            case "Napozóágy a tengerparton" -> personId.getSunBedAtBeach();
            case "Baldachin a tengerparton" -> personId.getBaldachin();
            case "Szekrény" -> personId.getLocker();
            default -> -1;
        };
    }

    //A paraméterként megadott szolgáltatást változtatja meg a personId példányba a második paraméter alapján
    private void SetServiceStatus(String serviceName, int UpOrDown)
    {
        switch (serviceName)
        {
            case "Szauna":
                personId.setSauna(personId.getSauna() + UpOrDown);
                break;
            case "Értékmegőrző":
                personId.setSafeDeposit(personId.getSafeDeposit() + UpOrDown);
                break;
            case "Pihenőágy":
                personId.setLounger(personId.getLounger() + UpOrDown);
                break;
            case "Napozóágy":
                personId.setSunBed(personId.getSunBed() + UpOrDown);
                break;
            case "Napozóágy a tengerparton":
                personId.setSunBedAtBeach(personId.getSunBedAtBeach() + UpOrDown);
                break;
            case "Baldachin a tengerparton":
                personId.setBaldachin(personId.getBaldachin() + UpOrDown);
                break;
            case "Szekrény":
                personId.setLocker(personId.getLocker() + UpOrDown);
                break;
        }
    }

    //Paraméterként megadott szolgáltatás árát adja vissza, amit a serviceRepositoryval kér le
    private int QuaryServicePrice(String serviceName) {
        List<Services> listOfEveryTicket = servicesRepository.findByType("Szolgaltatas").stream().toList();
        for (Services services : listOfEveryTicket) {
            if (services.getName().equals(serviceName)) {
                return services.getPrice();
            }
        }
        return 0;
    }

    //Listview beállítása, labelökhöz értékek rendelése, gombok tiltása/engedélyezése
    private void init() {
        Label_Id.setText(personId.getId());

        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        servicesRepository = context.getBean(ServicesRepository.class);
        //Lekérdezi a servicesRepositoryval a szolgáltatások neveit
        List<Services> ListOfSevices = servicesRepository.findByType("Szolgaltatas").stream().toList();
        List<String> services = new ArrayList<>();
        for (Services service : ListOfSevices) {
            services.add(service.getName());
        }
        //listview feltöltése a szolgáltatásokkal, első elemet kiválasztottra állítani, deleteButton tiltása
        ServicesList.setItems(FXCollections.observableList(services));
        ServicesList.getSelectionModel().select(0);
        changeAmountLabel(QuaryServiceStatus(ServicesList.getSelectionModel().getSelectedItem()));
        deleteButton.setDisable(true);

        //Gombnyomásra változzon a label ami a kiválasztott szolgáltatás darabszámát tartalmazza
        ServicesList.setOnMouseClicked(event -> {
            int status = QuaryServiceStatus(ServicesList.getSelectionModel().getSelectedItem());
            changeAmountLabel(status);
            if(status == 0)
            {
                deleteButton.setDisable(true);
            }
            if(status > 0)
            {
                deleteButton.setDisable(false);
            }
        });
    }


}