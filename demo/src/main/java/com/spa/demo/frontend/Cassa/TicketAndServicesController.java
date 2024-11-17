package com.spa.demo.frontend.Cassa;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketAndServicesController {

    private List<PersonId> personIdList = new ArrayList<>();
    private Buyer buyer;

    @FXML
    private ListView<String> Ids;

    @FXML
    void addServices(ActionEvent event) {

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
                GetTicketInfosFromTicketControll(ticketAddController.personId);
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
    }

    public void GetTicketInfosFromTicketControll(PersonId anotherPersonId)
    {
        String currentPersonId = anotherPersonId.getId();
        for (PersonId personId : personIdList) {
            if (personId.getId().equals(currentPersonId)) {
                personId = anotherPersonId;
            }
        }
    }

}
