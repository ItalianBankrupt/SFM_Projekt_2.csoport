package com.spa.demo.frontend.Cassa;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class SummaryController {

    ServicesRepository servicesRepository;
    ConfigurableApplicationContext context;

    @FXML
    private TextArea SummaryBox;

    public void getPersonIdsFromTicketsAndServices(List<PersonId> PersonIdList)
    {
        int balance = 0;
        for (PersonId personId : PersonIdList)
        {
            List<String> Services = personId.listServicesInfo();
            List<String> Tickets = personId.listTicketInfos();
            System.out.println(Services.toString());
            System.out.printf(Tickets.toString());

            String main = personId.getId() + ":\n";
            StringBuilder tickets = new StringBuilder("Jegyek:\n");

            for(String ticket : Tickets)
            {
                String singleTicketInfo = "";
                String[] info = ticket.split(":");
                int Price = QuaryPrice(info[0], "Belepo");
                singleTicketInfo = "\t" + ticket + " db Ár: " + Price + "Ft\n";
                balance += Price;
                tickets.append(singleTicketInfo);
            }

            StringBuilder services = new StringBuilder("Szolgáltatások:\n");
            for(String service : Services)
            {
                String singleServiceInfo = "";
                String[] info = service.split(":");
                int Price = QuaryPrice(info[0], "Szolgaltatas");
                singleServiceInfo = "\t" + service + " db Ár: " + Price + "Ft\n";
                balance += Price;
                services.append(singleServiceInfo);
            }

            SummaryBox.appendText(main + tickets + services);
            Tickets.clear();
            Services.clear();
        }
        String pay = "Fizetendő összeg: " + balance + "\n";
        SummaryBox.appendText(pay);
    }

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
