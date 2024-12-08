package com.spa.demo.frontend.Cassa.addcustomerControllers;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.*;
import com.spa.demo.frontend.Cassa.Models.Buyer;
import com.spa.demo.frontend.Cassa.Models.PersonId;
import com.spa.demo.frontend.Cassa.Utils.PopUpWindows;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import com.spa.demo.web.WebController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketAndServicesController {

    private RegistrationRepository registrationRepository;
    private IdentificationRepository identificationRepository;
    private CupboardRepository cupboardRepository;

    private final List<PersonId> personIdList = new ArrayList<>();
    private Buyer buyer;
    private final IntegerProperty balanceProperty = new SimpleIntegerProperty(0);


    @FXML
    private Label Label_CheckOut;

    @FXML
    private Label Label_balance;

    @FXML
    private TextField TextField_IdBalance;

    @FXML
    private ListView<String> Ids;

    Node node;

    //Bezárja azt az ablakot és visszatölti a MainPage-et
    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    //A kiválasztott azonosítóhoz tartózó PersonId példányt átküldi a ServicesAddController-nek és megnyitja az ehhez tartozó ablakot
    //Miután az be lesz zárva, beolvassa a servicessAddContorller personId példányát és összegét és meghívja a GetTicketInfosFromServicesControll metodust.
    @FXML
    void addServices(ActionEvent event) throws IOException {
        String selectedId = Ids.getSelectionModel().getSelectedItem();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(selectedId)) {
                FXMLLoader loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/addCustomerViews/ServicesAdd.fxml");
                ServicesAddController servicesAddController = loader.getController();
                servicesAddController.getPersonIdFromTicketAndServices(personId);
                WindowHandlerUtils.OpenScene(loader,"Jegy hozzáadása", "wait");
                GetTicketInfosAdd(servicesAddController.personId, servicesAddController.balance);
            }
        }
    }

    //Modosítja azt a PersonId példányt aminek az id-ja megegyezik a paraméterként kapott példány id-jával és modosítja a balance propertyt.
    private void GetTicketInfosAdd(PersonId anotherPersonId, int anotherBalance) {
        String currentPersonId = anotherPersonId.getId();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(currentPersonId)) {
                personId = anotherPersonId;
            }
        }
        int checkoutBalance = balanceProperty.get() + anotherBalance;
        balanceProperty.set(checkoutBalance);
    }

    //Gombnyomás után ellenörzi hogy az összes Id-hoz van e rendelve jegy, ha nem alert-et dob
    //Ha van akkor a buyer példány adatait feltölti a Registration táblába, a personId példányokat az Identification táblába a szekrényadatokat meg a CupBoard táblába
    //Info alert-tel jelzi a sikeres feltöltést
    @FXML
    void FinalizePurchase(ActionEvent event) throws IOException {
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
            String contentText ="A következő id(k) nem tartalmaznak jegyeket:" + String.join(", ",wrongIds);
            String headerText = "Rossz id(k)!";
            String title = "Rossz id";
            PopUpWindows.AlertWindow(contentText,headerText,title);
            return;
        }

        //----------Vásárló adatinak mentése
        Registration registration = Registration.builder()
                .City(buyer.getCity())
                .CostumerType(buyer.getStatus())
                .name(buyer.getName())
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
                    .personId(personId.getId())
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
                    .money(personId.getBalance().getValue())
                    .registration(registration)
                    .build();

            registration.getIdentifications().add(identification);

           WebController webController=new WebController();
            identificationRepository.save(identification);
           // webController.addIdentification();
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
        String contentText = "Azonosítók sikeres regisztrálása";
        String headerText = "Sikeres vásárlás";
        PopUpWindows.InfoWindow(contentText,headerText,headerText);
        Node node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    //Gomb megnyomására megnyit egy ablakot ami a summary.fxml ad, és a SummaryController publikus metodusának átküldi a personId példányokat
    //tartalmazó listát.
    @FXML
    void OpenSummary(ActionEvent event) throws IOException {
        FXMLLoader loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/addCustomerViews/Summary.fxml");
        SummaryController summary = loader.getController();
        summary.getPersonIdsFromTicketsAndServices(personIdList);
        WindowHandlerUtils.OpenScene(loader,"Azonosítók összegzése", "wait");
    }

    //A kiválasztott azonosítóhoz tartózó PersonId példányt átküldi a TicketsAddController-nek és megnyitja az ehhez tartozó ablakot
    //Miután az be lesz zárva, beolvassa a ticketsAddController personId példányát és összegét és meghívja a GetTicketInfosFromServicesControll metodust.
    @FXML
    void addTickets(ActionEvent event) throws IOException {
        String selectedId = Ids.getSelectionModel().getSelectedItem();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(selectedId)) {
                FXMLLoader loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/addCustomerViews/TicketAdd.fxml");
                TicketsAddController ticketsAddController = loader.getController();
                ticketsAddController.getPersonIdFromTicketAndServices(personId);
                WindowHandlerUtils.OpenScene(loader,"Jegy hozzáadása", "wait");
                GetTicketInfosAdd(ticketsAddController.personId, ticketsAddController.balance);
            }
        }
    }

    //A textFieldbe írt értéket kiolvassa és modositja a personId pédányhoz tartozó egyenleget
    @FXML
    void balanceAddButton(ActionEvent event) {
        int updateMoney = Integer.parseInt(TextField_IdBalance.getText());
        updateBalance(updateMoney);
        balanceProperty.set(balanceProperty.get() + updateMoney);
        TextField_IdBalance.setText("");
    }

    //Ellenörzi hogy a personId példány egyenlegéből kivonható-e az összeg ha nem Alert-et dob
    //Ha kivonható akkor modosítja a personId példány egyenlegét
    @FXML
    void balanceDeleteButton(ActionEvent event) {
        if(Integer.parseInt(TextField_IdBalance.getText())  > identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance().getValue())
        {
            String contentText = "A beírt egyenleg nagyobb mint a meglévő egyenleg";
            String headerText = "Hibás egyenleg törlés!";
            String title = "Hibás egyenleg törlés";
            PopUpWindows.AlertWindow(contentText,headerText,title);
        }
        else{
            updateBalance(-1*Integer.parseInt(TextField_IdBalance.getText()));
            balanceProperty.set(balanceProperty.get() - Integer.parseInt(TextField_IdBalance.getText()));
        }
        TextField_IdBalance.setText("");
    }

    //IdHandlertől kapott buyer példány beolvasása initialization() metodus meghívása
    public void receiveBuyer(Buyer otherBuyer)
    {
        buyer = otherBuyer;
        initialization();
    }

    //Balance label értékének megváltoztatása a paraméterként kapott int-tel
    private void updateBalance(int balance)
    {
        int currentBalance = identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance().getValue();
        identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance().setValue(currentBalance + balance);
    }

    //Paraméterként kapott string alapján (feltételezve hogy az id) visszadja a megfelelő personId példányt
    //ha nem talál ilyet akkor null-t ad vissza
    private PersonId identifyPerson(String id)
    {
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(id)) {
                return personId;
            }
        }
        return null;
    }

    //Listview beállítása, label-ök integerPropertyhez kötése
    private void initialization() {
        //Ids id-jű listViewra beállítja a buyer példányhoz tartozó observable listet
        Ids.setItems(buyer.getIds());
        //Létrehozza az azonosítókból a personId példányokat
        for(int i = 0; i < buyer.getIds().size(); i++)
        {
            PersonId personId = new PersonId(buyer.getIds().get(i), buyer.getIds().get(0));
            personIdList.add(personId);
        }
        Ids.getSelectionModel().select(0);
        //Összeköti a checkout és balance labelöket StringPropertykel így azok értéke ha modosul akkor a label értéke is fog
        Label_CheckOut.textProperty().bind(Bindings.convert(balanceProperty));

        Label_balance.textProperty().bind(Bindings.convert(identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance()));

        //TextFieldbe csak számokat lehessen írni, ezért listener ráállítása
        TextField_IdBalance.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                boolean isDigit = true;
                if(newValue.length() > oldValue.length())
                {
                    isDigit = Character.isDigit(newValue.charAt(newValue.length()-1));
                }
                if(isDigit)
                {
                    TextField_IdBalance.setText(newValue);
                }
                else
                {
                    TextField_IdBalance.setText(oldValue);
                }
            }
        });

        //HA a listviewba új id-t választunk ki akkor a balance label-t annak az personId példányhoz tartozó balanceval köti össze
        Ids.setOnMouseClicked(mouseEvent -> {
            Label_balance.textProperty().bind(Bindings.convert(identifyPerson(Ids.getSelectionModel().getSelectedItem()).getBalance()));
        });

        //repository-k inicializálása
        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        registrationRepository = context.getBean(RegistrationRepository.class);
        identificationRepository = context.getBean(IdentificationRepository.class);
        cupboardRepository = context.getBean(CupboardRepository.class);
    }
}
