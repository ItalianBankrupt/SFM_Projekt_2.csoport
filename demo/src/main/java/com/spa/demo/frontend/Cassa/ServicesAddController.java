package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class ServicesAddController {

    public PersonId personId;
    public int balance = 0;
    private ServicesRepository servicesRepository;

    @FXML
    private Label IdLabel;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<String> ServicesList;

    @FXML
    private Label amountLabel;

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

    @FXML
    void Finalize(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }

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

    public void getPersonIdFromTicketAndServices(PersonId AnotherpersonId) {
        personId = AnotherpersonId;
        init();
    }

    private void init() {
        IdLabel.setText(personId.getId());
        SpringManager springManager = new SpringManager();
        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        servicesRepository = context.getBean(ServicesRepository.class);
        List<Services> ListOfSevices = servicesRepository.findByType("Szolgaltatas").stream().toList();
        List<String> services = new ArrayList<>();
        for (Services service : ListOfSevices) {
            services.add(service.getName());
        }

        ServicesList.setItems(FXCollections.observableList(services));
        ServicesList.getSelectionModel().select(0);
        changeAmountLabel(QuaryServiceStatus(ServicesList.getSelectionModel().getSelectedItem()));
        deleteButton.setDisable(true);

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

    private void changeAmountLabel(int status)
    {
        String base = "Mennyiség:";
        amountLabel.setText(base.concat(status + ""));
    }

    private int QuaryServicePrice(String serviceName) {
        List<Services> listOfEveryTicket = servicesRepository.findByType("Szolgaltatas").stream().toList();
        for (Services services : listOfEveryTicket) {
            if (services.getName().equals(serviceName)) {
                return services.getPrice();
            }
        }
        return 0;
    }

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

}