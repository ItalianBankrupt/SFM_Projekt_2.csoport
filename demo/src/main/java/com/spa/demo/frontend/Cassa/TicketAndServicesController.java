package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private IdentificationRepository identificationRepository;
    private CupboardRepository cupboardRepository;

    private List<PersonId> personIdList = new ArrayList<>();
    private Buyer buyer;
    private int CheckoutBalance;
    private final IntegerProperty balanceProperty = new SimpleIntegerProperty(0);


    @FXML
    private Label idBalanceLabel;

    @FXML
    private TextField idBalanceTextBox;


    @FXML
    private Label checkoutBalanceLabel;

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
        CheckoutBalance = balanceProperty.get() + anotherBalance;
        balanceProperty.set(CheckoutBalance);
    }

    @FXML
    void FinalizePurchase(ActionEvent event) {
        List<String> wrongIds = new ArrayList<>();
        for(PersonId personId:personIdList)
        {
            boolean zeroValue = true;
            for (String tickets:personId.listTicketInfos())
            {
                String[] info = tickets.split(":");
                if(!info[1].equals("0"))
                {
                    zeroValue = false;
                    break;
                }
            }
            if (zeroValue){wrongIds.add(personId.getId());}
        }
        if(!wrongIds.isEmpty())
        {
            Alert missingData = new Alert(Alert.AlertType.ERROR);
            String stringOfWrongIds = String.join(", ", wrongIds);
            missingData.setContentText("A következő id(k) nem tartalmaznak jegyeket:" +stringOfWrongIds);
            missingData.setHeaderText("Rossz id(k)!");
            missingData.setTitle("Rossz id");
            missingData.showAndWait();
            return;
        }

        //----------Vásárló adatinak mentése
        Registration registration = Registration.builder()
                .City(buyer.getCity())
                .CostumerType(buyer.getStatus())
                .Name(buyer.getName())
                .IDNumber(buyer.getId())
                .PostCode(buyer.getPostCode())
                .Street(buyer.getStreet())
                .GeneratedId(personIdList.get(0).getId())
                .identifications(new ArrayList<>())
                .build();

        registrationRepository.save(registration);
        //----------------------------------

        //---------Azonosítók mentése-------
        for (PersonId personId : personIdList) {
            Identification identification = Identification.builder()
                    .PersonId(personId.getId())
                    .AdultFellingTicket(personId.getAdultFellingTicket())
                    .StudentFellingTicket(personId.getStudentFellingTicket())
                    .AdultThermalTicket(personId.getAdultThermalTicket())
                    .PensionerThermalTicket(personId.getPensionerThermalTicket())
                    .StudentThermalTicket(personId.getStudentThermalTicket())
                    .AdultBeachTicket(personId.getAdultBeachTicket())
                    .PensionerBeachTicket(personId.getPensionerBeachTicket())
                    .StudentBeachTicket(personId.getStudentBeachTicket())
                    .AquaParkTicket(personId.getAquaParkTicket())
                    .PremiumTicket(personId.getPremiumTicket())
                    .Sauna(personId.getSauna())
                    .SafeDeposit(personId.getSafeDeposit())
                    .Lounger(personId.getLounger())
                    .SunBed(personId.getSunBed())
                    .SunBedAtTheBeach(personId.getSunBedAtBeach())
                    .Baldachin(personId.getBaldachin())
                    .Money(personId.getBalance().getValue())
                    .registration(registration)
                    .build();

            registration.getIdentifications().add(identification);
            identificationRepository.save(identification);
            //----------Szekrények személyhez rendelése------
            if(personId.getLocker() != 0)
            {
                for (int i = 0; i < personId.getLocker(); i++)
                {
                    Cupboard cupboard = Cupboard.builder()
                            .status(1)
                            .identification(identification)
                            .build();

                    cupboardRepository.save(cupboard);
                }
            }
        }
        Node node = (Node) event.getSource();
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
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

    @FXML
    void balanceAddButton(ActionEvent event) {
        int updateMoney = Integer.parseInt(idBalanceTextBox.getText());
        updateBalance(updateMoney);
        balanceProperty.set(balanceProperty.get() + updateMoney);
        idBalanceTextBox.setText("");
    }

    @FXML
    void balanceDeleteButton(ActionEvent event) {
        if(Integer.parseInt(idBalanceTextBox.getText())  > identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance().getValue()){
            Alert WrongInput = new Alert(Alert.AlertType.ERROR);
            WrongInput.setContentText("A beírt egyenleg nagyobb mint a meglévő egyenleg");
            WrongInput.setHeaderText("Hibás egyenleg törlés!");
            WrongInput.setTitle("Hibás egyenleg törlés");
            WrongInput.showAndWait();
        }
        else{
            updateBalance(-1*Integer.parseInt(idBalanceTextBox.getText()));
            balanceProperty.set(balanceProperty.get() - Integer.parseInt(idBalanceTextBox.getText()));
        }
        idBalanceTextBox.setText("");
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
        checkoutBalanceLabel.textProperty().bind(Bindings.convert(balanceProperty));

        idBalanceLabel.textProperty().bind(Bindings.convert(identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance()));


        Ids.setOnMouseClicked(mouseEvent -> {
            idBalanceLabel.textProperty().bind(Bindings.convert(identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance()));
        });

        context = SpringManager.getApplicationContext();
        registrationRepository = context.getBean(RegistrationRepository.class);
        identificationRepository = context.getBean(IdentificationRepository.class);
        cupboardRepository = context.getBean(CupboardRepository.class);
    }

    private void updateBalance(int balance)
    {
        int currentBalance = identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance().getValue();
        identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance().setValue(currentBalance + balance);
    }

    private PersonId identifyPerson(String id)
    {
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(id)) {
                return personId;
            }
        }
        return null;
    }

    public void GetTicketInfosFromTicketControll(PersonId anotherPersonId, int anotherBalance)
    {
        String currentPersonId = anotherPersonId.getId();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(currentPersonId)) {
                personId = anotherPersonId;
            }
        }
        CheckoutBalance = balanceProperty.get() + anotherBalance;
        balanceProperty.set(CheckoutBalance);
    }

}
