package com.spa.demo.frontend.Cassa.addcustomerControllers;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import com.spa.demo.frontend.Cassa.Models.PersonId;
import com.spa.demo.frontend.Cassa.Utils.WindowHandlerUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.List;

public class SummaryController {

    ServicesRepository servicesRepository;
    ConfigurableApplicationContext context;

    @FXML
    private TextArea TextArea_SummaryBox;

    //Gombnyomásra bezárja az ablakot
    @FXML
    void Close(ActionEvent event) {
        Node node = (Node) event.getSource();
        WindowHandlerUtils.CloseScene(node);
    }

    //Parametere egy personId-kat tartalmazó lista
    //ezekhez a personIdhoz tartozó infokat írja ki egy textArea-ba
    public void getPersonIdsFromTicketsAndServices(List<PersonId> PersonIdList)
    {
        int balance = 0;
        for (PersonId personId : PersonIdList)
        {
            //az adott personId-hoz tartozó nem nulla darabszámú jegyek és szolgáltatások listája
            List<String> Services = personId.listServicesInfo();
            List<String> Tickets = personId.listTicketsWithoutZeroValues();

            String main = personId.getId() + ":\n";
            StringBuilder tickets = new StringBuilder("Jegyek:\n");
            //Jegyek neve darabszáma és ára
            for(String ticket : Tickets)
            {
                String singleTicketInfo = "";
                String[] info = ticket.split(":");
                int Price = QuaryPrice(info[0], "Belepo") * Integer.parseInt(info[1]);
                singleTicketInfo = "\t" + ticket + " db Ár: " + Price + "Ft\n";
                balance += Price;
                tickets.append(singleTicketInfo);
            }
            //Szolgáltatások neve darabszáma és ára
            StringBuilder services = new StringBuilder("Szolgáltatások:\n");
            for(String service : Services)
            {
                String singleServiceInfo = "";
                String[] info = service.split(":");
                int Price = QuaryPrice(info[0], "Szolgaltatas") * Integer.parseInt(info[1]);
                singleServiceInfo = "\t" + service + " db Ár: " + Price + "Ft\n";
                balance += Price;
                services.append(singleServiceInfo);
            }
            TextArea_SummaryBox.appendText(main + tickets + services);
            Tickets.clear();
            Services.clear();
        }
        String pay = "Fizetendő összeg: " + balance + "\n";
        TextArea_SummaryBox.appendText(pay);
    }

    //Az első paraméter a jegy/szolgáltatás nevét határozza meg, a második paraméter, hogy mi a tipusa, ez lehet Szolgaltatas vagy Belepo
    //Visszatérési értéke az adott jegy vagy szolgáltatás ára
    private int QuaryPrice(String name, String type)
    {
        context = SpringManager.getApplicationContext();
        servicesRepository = context.getBean(ServicesRepository.class);
        List<Services> listOfServices = servicesRepository.findByType(type).stream().toList();
        for (Services services:listOfServices)
        {
            if(services.getName().equals(name))
            {
                return services.getPrice();
            }
        }
        return 0;
    }

}
