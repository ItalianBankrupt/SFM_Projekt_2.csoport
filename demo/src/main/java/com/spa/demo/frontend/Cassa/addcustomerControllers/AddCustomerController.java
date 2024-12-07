package com.spa.demo.frontend.Cassa.addcustomerControllers;

import com.spa.demo.frontend.Cassa.Models.Buyer;
import com.spa.demo.frontend.Cassa.Utils.JavaFxElementModifier;
import com.spa.demo.frontend.Cassa.Utils.PopUpWindows;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@ComponentScan("com.spa.demo.backend")
public class AddCustomerController {

    @FXML
    private RadioButton RadioButton_Adult;

    @FXML
    private RadioButton RadioButton_Pensioner;

    @FXML
    private RadioButton RadioButton_Student;

    @FXML
    private TextField TextField_CustomerCity;

    @FXML
    private TextField TextField_CustomerId;

    @FXML
    private TextField TextField_CustomerName;

    @FXML
    private TextField TextField_CustomerPostCode;

    @FXML
    private TextField TextField_CustomerStreet;

    @FXML
    private ToggleGroup buyerAgeRange;

    Buyer buyer = new Buyer("","","","","",0);
    Node node;

    //Bezárja azt az ablakot és visszatölti a MainPage-et
    @FXML
    void backToMainPage(ActionEvent event) throws IOException {
        node = (Node) event.getSource();
        WindowHandlerUtils.BackToCassaMainPage(node);
    }

    //Metodus amivel az AddCustomerController és IdHandlerController kommunikál
    //Ezt a publikus metodust használja az IdHandlerController hogy a Buyer osztály egy példányát modositsa
    //A paraméterül megkapott buyer példány adataival tölti fel a TextFieldeket és állítja be a rádiógombokat
    public void SendBuyerToAddCustomer(Buyer sentBuyer)
    {
        TextField_CustomerName.setText(sentBuyer.getName());
        TextField_CustomerId.setText(sentBuyer.getId());
        TextField_CustomerCity.setText(sentBuyer.getCity());
        TextField_CustomerStreet.setText(sentBuyer.getStreet());
        TextField_CustomerPostCode.setText(sentBuyer.getPostCode());
        if(sentBuyer.getStatus() == 1)
        {
            RadioButton_Adult.setSelected(true);
        }
        else if(sentBuyer.getStatus() == 2)
        {
            RadioButton_Student.setSelected(true);
        }
        else if(sentBuyer.getStatus() == 3)
        {
            RadioButton_Pensioner.setSelected(true);
        }
        this.buyer = sentBuyer;
    }

    //Beírt adatok ellenörzése,hibaüzenetek megjelenítése, következő oldal megnyitása, adatok átküldése.
    @FXML
    void nextToIdPage(ActionEvent event) throws IOException {

        //TextFieldek és rádiógombok hátterének megfelelő színre állítása állítása
        JavaFxElementModifier.changeTextFieldBackgroundColor("white",TextField_CustomerName,TextField_CustomerId,TextField_CustomerCity,TextField_CustomerPostCode,TextField_CustomerStreet);
        JavaFxElementModifier.changeRadioButtonBackgroundColor("#d5c990", RadioButton_Adult,RadioButton_Student,RadioButton_Pensioner,RadioButton_Student);
        //TextFieldek értékeinek kiolvasása
        String name = TextField_CustomerName.getText();
        String postcode = TextField_CustomerPostCode.getText();
        String city = TextField_CustomerCity.getText();
        String street = TextField_CustomerStreet.getText();
        String id = TextField_CustomerId.getText();
        int status = 0;

        //RadioButton értékének kiolvasása
        if(RadioButton_Adult.isSelected())
        {
            status = 1;
        }
        else if(RadioButton_Pensioner.isSelected())
        {
            status = 3;
        }
        else if(RadioButton_Student.isSelected())
        {
            status = 2;
        }

        //Lista ami a kitöltetlen textFieldeket vagy Radiogombokat tartalmazza
        List<String> missingInputs = new ArrayList<String>();

        //Ellenörzi, hogy mindegyik textfield-be lett e írva, ha nem akkor hátterét pirosra változtatja
        if (name.isEmpty()) {
            JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_CustomerName);
            missingInputs.add("Név");
        }
        if (postcode.isEmpty()) {
            JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_CustomerPostCode);
            missingInputs.add("Irányítószám");
        }
        if (city.isEmpty()) {
            JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_CustomerCity);
            missingInputs.add("Település");
        }
        if (street.isEmpty()) {
            JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_CustomerStreet);
            missingInputs.add("Utca,házszám");
        }
        if (id.isEmpty()) {
            JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_CustomerId);
            missingInputs.add("Szemlyégigazolvány szám");
        }

        //Ellenörzi hogy a ToggleGrouphoz tartozó rádiógombok valamelyike ki lett e választva, ha nem hátteret pirosra állítja
        if (buyerAgeRange.getSelectedToggle() == null)
        {
            missingInputs.add("diák/felnőtt/nyugdíjas");
            JavaFxElementModifier.changeRadioButtonBackgroundColor("red", RadioButton_Pensioner,RadioButton_Adult,RadioButton_Student);
        }

        //Ha nem üres az üres TextFieldeket tartalmazó lista akkor meghív egy Alert ablakot
        if (!missingInputs.isEmpty()) {
            String contentText = "A következő adat hiányos:"+ String.join(",", missingInputs);
            String headerText = "Hiányos adatok!";
            String title = "Hibás vásárlói adatok";
            PopUpWindows.AlertWindow(contentText,title,headerText);
        }

        String correctInfoStatus = checkInfos(postcode, id);

        //Ha nincs üres TextField akkor leelenörzi, hogy az irányítószám és személyigazolványszám megfelelő hosszuságú-e
        //Ha nem akkor pirosra változtaja a hátterét és meghív egy Alert-et
        if(!correctInfoStatus.equals("none") && missingInputs.isEmpty()) {
            switch(correctInfoStatus)
            {
                case "Irányítószám":
                    JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_CustomerPostCode);
                    break;
                case "Személyigazolványszám":
                    JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_CustomerId);
                    break;
                case "Irányítószám és személyigazolványszám":
                    JavaFxElementModifier.changeTextFieldBackgroundColor("red", TextField_CustomerPostCode,TextField_CustomerId);
                    break;
            }
            String contentText = "A következő mező hibás:" + correctInfoStatus;
            String headerText = "Hibás adatok!";
            String title = "Hibás adatok";
            PopUpWindows.AlertWindow(contentText,title,headerText);
        }

        //Ha nincs üres textfield és nem hibásak az értékek akkor leellenorzi, akkor a buyer példányt
        //értékekkel tölti fel és megnyitja a következő ablakot
        if(correctInfoStatus.equals("none") && missingInputs.isEmpty())
        {
            //Ez arra szolgál ha már volt buyer adatokkal feltöltve, de modosítjuk akkor változott e az id
            //Ha igen akkor az összes már létrehozott ID-t modosítjuk a modosított személyigazolvány számmal
            //Mivel alapból inicializálva van egy buyer csupa üres értékkel, így első felvételnél is különböző lesz
            //és modosítja
            if(!(buyer.getId().equals(id)))
            {
                buyer.setId(id);
                buyer.setNumberOfGeneratedId(0);
                buyer.UpdateList(buyer.getIds().size(), "All");
            }
            //Ha változott a statusz modosításnál akkor modosítja az első, magához a vásárlóhoz tartozó értéket.
            if(buyer.getStatus() != status)
            {
                buyer.setStatus(status);
                buyer.UpdateList(buyer.getIds().size(), "FirstOnly");
            }
            else {buyer.setStatus(status);}

            //buyer példány további (azonosító generálása során lényegtelen értékek) megadása
            buyer.setName(name);
            buyer.setPostCode(postcode);
            buyer.setCity(city);
            buyer.setStreet(street);

            //Következő ablak betöltése, inicializáló metódus meghívása és buyer példány átküldése, ablak bezárása
            FXMLLoader loader = WindowHandlerUtils.getFXMLoader("/fxml/CassaGUI/addCustomerViews/IdHandler.fxml");
            IdHandlerController idHandlerController = loader.getController();
            idHandlerController.sendBuyerInfos(buyer);
            idHandlerController.initialization();
            node = (Node) event.getSource();
            WindowHandlerUtils.OpenScene(loader,"Vásárlóhoz tartozó id(k) kezelése", node);
        }
    }

    //Paraméterként két Stringet vár, az első az irányítószám a második a személyigazolvány szám
    //Visszatérési értéke Irányítószám és személyigazolványszám, ha az irányítószám nem 4 karakter hosszúságú illerve az Id nem 8
    //Irányítószám-ot add vissza ha csak az irányítószám hossza nem jó, Személyigazolványszám-t ha az ID hossza hibás, none-t ha minden jó
    public String checkInfos(String postCode, String ID)
    {
        if (postCode.length() != 4 && ID.length() != 8)
            return "Irányítószám és személyigazolványszám";
        else if (postCode.length() != 4)
            return "Irányítószám";
        else if (ID.length() != 8)
            return "Személyigazolványszám";
        else
            return "none";
    }

    //A stage betöltése után egyből lefut
    //A TextField_CustomerPostCode mezőre beállít egy listener ami nem engedi hogy karaktert irjunk
    //illetve, hogy 4 számnál hosszabb legyen
    //A TextField_CustomerId mezőre is állít egy listener ami nem engedi hogy 8 karakternél hosszabb legyen
    public void initialize()
    {
        TextField_CustomerPostCode.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                boolean isDigit = true;
                if(newValue.length() > oldValue.length())
                {
                    isDigit = Character.isDigit(newValue.charAt(newValue.length()-1));
                }
                if(newValue.length() < 5 && isDigit)
                {
                    TextField_CustomerPostCode.setText(newValue);
                }
                else
                {
                    TextField_CustomerPostCode.setText(oldValue);
                }
            }
        });
        TextField_CustomerId.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if(newValue.length() > 8)
                {
                    TextField_CustomerId.setText(oldValue);
                }
                else
                {
                    TextField_CustomerId.setText(newValue);
                }
            }
        });
    }

}
