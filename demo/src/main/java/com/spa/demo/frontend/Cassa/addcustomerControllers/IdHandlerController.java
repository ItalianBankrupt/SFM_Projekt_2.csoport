package com.spa.demo.frontend.Cassa.addcustomerControllers;

import com.spa.demo.backend.RegistrationRepository;
import com.spa.demo.frontend.Cassa.Models.Buyer;
import com.spa.demo.frontend.Cassa.Utils.JavaFxElementModifier;
import com.spa.demo.frontend.Cassa.Utils.PopUpWindows;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Objects;

@Controller
public class IdHandlerController {

    public RegistrationRepository registrationRepository;

    @FXML
    private Button AddId;
    private Buyer buyer;

    @FXML
    private ListView<String> ListOfIds;

    @FXML
    private RadioButton RadioButton_Adult;

    @FXML
    private RadioButton RadioButton_Pensioner;

    @FXML
    private RadioButton RadioButton_Student;

    @FXML
    private ToggleGroup buyerAgeRange;

    Node node;

    //Bezárja azt az ablakot és visszatölti a MainPage-et
    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    //Leellenörzi, hogy a toogleGroupba tartozó rádiógombok közül ki lett e választva valamelyik, ha nem Alert-et dob
    //Ha lett kiválasztva akkor azzal létrehoz egy azonosított
    @FXML
    void AddId(ActionEvent event) throws IOException {
        if (buyerAgeRange.getSelectedToggle() == null)
        {
            JavaFxElementModifier.changeRadioButtonBackgroundColor("red", RadioButton_Adult,RadioButton_Pensioner,RadioButton_Student);
            String contentText = "Válassza a jegy típusát:Diák/felnőtt/nyugdíjas";
            String headerText = "Nincs kiválasztott típus";
            String title = "Hiba";
            PopUpWindows.AlertWindow(contentText,headerText,title);
        }
        else{
            JavaFxElementModifier.changeRadioButtonBackgroundColor("#d5c990",RadioButton_Student,RadioButton_Adult,RadioButton_Pensioner);
            int status = -1;
            if(RadioButton_Student.isSelected())
            {
                status = 2;
                RadioButton_Student.setSelected(false);
            }
            else if(RadioButton_Adult.isSelected())
            {
                status = 1;
                RadioButton_Adult.setSelected(false);
            }
            else if(RadioButton_Pensioner.isSelected())
            {
                status = 3;
                RadioButton_Pensioner.setSelected(false);
            }
            buyer.GenerateId(status);
        }
    }

    public void GenID(int Status)
    {
        buyer.GenerateId(Status);
    }

    //Leellenörzi, hogy nem az elsőt akarjuk e törölni, ha igen akkor Alert-et dob fel
    //Ellenkező esetben törli a kivalasztott azonositot a listabol és a legenerált azonositok számát csökkenti eggyel
    @FXML
    void RemoveId(ActionEvent event) {
        if(Objects.equals(ListOfIds.getSelectionModel().getSelectedItem(), buyer.getIds().get(0)))
        {
            String contentText = "Az első azonosító nem törölhető, módosítsd a vevő adatait!";
            String headerText = "Nem törölhető";
            String title = "Hiba";
            PopUpWindows.AlertWindow(contentText, headerText, title);
            return;
        }
        String removeId = ListOfIds.getSelectionModel().getSelectedItem();
        buyer.RemoveId(removeId);
        buyer.setNumberOfGeneratedId(buyer.getNumberOfGeneratedId()- 1);
    }

    //A gomb megnyomása után AddCustomerController publikus metodusát meghívja, ezzel beállítva annak a buyer példányt
    //megnyitja az új ablakot és bezárja ezt
    @FXML
    void changeBuyerInfos(ActionEvent event) throws IOException {
        FXMLLoader loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/addCustomerViews/addCustomer.fxml");
        AddCustomerController addCustomerController = loader.getController();
        addCustomerController.SendBuyerToAddCustomer(buyer);
        Node node = (Node) event.getSource();
        WindowHandlerUtils.OpenScene(loader,"Új vásárló hozzáadása", node);
    }

    //Gomb megnyomása után leellenorzi hogy van e generált azonosító (elméletbe nem történhet meg, de kitudja), ha nincs
    //akkor alert meghivása.
    //Ha nincs hiba akkor a következő ablak megnyitása(ez a TicketsAndServices) buyer példány átküldése és jelenlegi ablak bezárása
    @FXML
    void GoToTicketsAndServices(ActionEvent event) throws IOException {
        if(buyer.getIds().isEmpty())
        {
            String contentText ="Nincsenek legenerált ID-k";
            String headerText ="Nincs ID!";
            String title ="Hiba";
            PopUpWindows.AlertWindow(contentText,headerText,title);
            return;
        }

        FXMLLoader loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/addCustomerViews/TicketsAndServices.fxml");
        TicketAndServicesController ticketAndServicesController = loader.getController();
        ticketAndServicesController.receiveBuyer(buyer);
        Node node = (Node) event.getSource();
        WindowHandlerUtils.OpenScene(loader, "Jegyek és szolgáltatások hozzáadása", node);
    }

    //Ezzel a publikus metodussal "küldi" át az AddCustomerController a buyer példányt
    public void sendBuyerInfos(Buyer anotherBuyer)
    {
        this.buyer = anotherBuyer;
    }

    //Inicializáláskor a buyer példányba található observable listet beállítja a ListOfIds nevű listview-ra
    //És ha üres a listview(első megnyitás, tehát nem a már modosított adatos) akkor betölti az első azonosítot ami
    //a buyer azonosítója lesz
    public void initialization()
    {
        if(buyer.getIds().isEmpty()) {
            AddId.fire();
        }
        ListOfIds.setItems(buyer.getIds());
    }
}
