package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Identification;
import com.spa.demo.backend.IdentificationRepository;
import com.spa.demo.backend.Registration;
import com.spa.demo.backend.RegistrationRepository;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketAndServicesController {

    private ConfigurableApplicationContext context;
    private RegistrationRepository registrationRepository;

    private List<PersonId> personIdList = new ArrayList<>();
    private Buyer buyer;
    private int balance;
    private final IntegerProperty balanceProperty = new SimpleIntegerProperty(0);
    @FXML
    private Label balanceLabel;

    @FXML
    private ListView<String> Ids;

    @FXML
    void addServices(ActionEvent event) throws IOException {
        String selectedId = Ids.getSelectionModel().getSelectedItem();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(selectedId)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CassaGUI/ServicesAdd.fxml"));
                Parent root = loader.load();
                ServicesAddController servicesAddController = loader.getController();
                servicesAddController.getPersonIdFromTicketAndServices(personId);
                Stage stage = new Stage();
                stage.setTitle("Ticket add");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                GetTicketInfosFromServicesControll(servicesAddController.personId, servicesAddController.balance);
            }
        }
    }

    private void GetTicketInfosFromServicesControll(PersonId anotherPersonId, int anotherBalance) {
        String currentPersonId = anotherPersonId.getId();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(currentPersonId)) {
                personId = anotherPersonId;
            }
        }
        balance = balanceProperty.get() + anotherBalance;
        balanceProperty.set(balance);
    }

    @FXML
    void FinalizePurchase(ActionEvent event) {
        SpringManager springManager = new SpringManager();
        context = springManager.getApplicationContext();
        registrationRepository = context.getBean(RegistrationRepository.class);
        Registration reg = Registration.builder()
                .City(buyer.getCity())
                .CostumerType(buyer.getStatus())
                .Name(buyer.getName())
                .IDNumber(buyer.getId())
                .PostCode(buyer.getPostCode())
                .Street(buyer.getStreet())
                .GeneratedId(buyer.getId())
                .build();
        registrationRepository.save(reg);
    }

    @FXML
    void OpenSummary(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CassaGUI/Summary.fxml"));
        Parent root = loader.load();
        SummaryController summary = loader.getController();
        summary.getPersonIdsFromTicketsAndServices(personIdList);
        Stage stage = new Stage();
        stage.setTitle("Summary");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


    @FXML
    void addTickets(ActionEvent event) throws IOException {
        String selectedId = Ids.getSelectionModel().getSelectedItem();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(selectedId)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CassaGUI/TicketAdd.fxml"));
                Parent root = loader.load();
                TicketAddController ticketAddController = loader.getController();
                ticketAddController.getPersonIdFromTicketAndServices(personId);
                Stage stage = new Stage();
                stage.setTitle("Ticket add");
                stage.setScene(new Scene(root));
                stage.showAndWait();
                GetTicketInfosFromTicketControll(ticketAddController.personId, ticketAddController.balance);
            }
        }
    }

    public void receiveBuyer(Buyer otherBuyer)
    {
        buyer = otherBuyer;
        init();
    }

    private void init() {
        Ids.setItems(buyer.getIds());
        for(int i = 0; i < buyer.getIds().size(); i++)
        {
            PersonId personId = new PersonId(buyer.getIds().get(i), buyer.getIds().get(0));
            personIdList.add(personId);
        }
        Ids.getSelectionModel().select(0);
        balanceLabel.textProperty().bind(Bindings.convert(balanceProperty));
    }

    public void GetTicketInfosFromTicketControll(PersonId anotherPersonId, int anotherBalance)
    {
        String currentPersonId = anotherPersonId.getId();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(currentPersonId)) {
                personId = anotherPersonId;
            }
        }
        balance = balanceProperty.get() + anotherBalance;
        balanceProperty.set(balance);
    }

}
