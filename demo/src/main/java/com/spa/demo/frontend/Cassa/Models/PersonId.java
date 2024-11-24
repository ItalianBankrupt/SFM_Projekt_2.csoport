package com.spa.demo.frontend.Cassa.Models;

import com.spa.demo.SpringManager;
import com.spa.demo.backend.Cupboard;
import com.spa.demo.backend.CupboardRepository;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import lombok.*;

import com.spa.demo.backend.Identification;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class PersonId
{
    //Infos from buyerClass
    private String id;
    private String buyerId;

    //Tickets and Services 0-if doesn't have that type 1-if have
    private int AdultFellingTicket = 0;
    private int StudentFellingTicket = 0;
    private int FeelingPensionerTicket = 0;
    private int AdultBeachTicket = 0;
    private int StudentBeachTicket = 0;
    private int PensionerBeachTicket = 0;
    private int AdultThermalTicket = 0;
    private int StudentThermalTicket = 0;
    private int PensionerThermalTicket = 0;
    private int Sauna = 0;
    private int SafeDeposit = 0;
    private int Lounger = 0;
    private int SunBed = 0;
    private int SunBedAtBeach = 0;
    private int Baldachin = 0;
    private int AquaParkTicket = 0;
    private int PremiumTicket = 0;
    private int Locker = 0;

    //Balance info
    private IntegerProperty Balance = new SimpleIntegerProperty(0);

    //Konstruktor csak két paramétert vár, első paraméter az egyedi azonosító, a második pedig a vevő azonosítója
    //A többi érték alapból 0-ra van állítva
    public PersonId(String id,String buyerId)
    {
        this.id = id;
        this.buyerId = buyerId;
    }

    public PersonId(Identification identification)
    {
        ConfigurableApplicationContext context = SpringManager.getApplicationContext();
        CupboardRepository cupboardRepository = context.getBean(CupboardRepository.class);
        List<Cupboard> lockers = cupboardRepository.findAll();
        this.Locker = 0;
        for(Cupboard locker : lockers)
        {
            if(locker.getIdentification().getPersonId().equals(id))
            {
                Locker++;
            }
        }

        this.id = identification.getPersonId();
        this.buyerId = identification.getPersonId();
        this.AdultBeachTicket = identification.getAdultBeachTicket();
        this.StudentBeachTicket = identification.getStudentBeachTicket();
        this.PensionerBeachTicket = identification.getPensionerBeachTicket();
        this.FeelingPensionerTicket = identification.getFeelingPensionerTicket();
        this.AdultFellingTicket = identification.getAdultFellingTicket();
        this.StudentFellingTicket = identification.getStudentFellingTicket();
        this.AdultThermalTicket = identification.getAdultThermalTicket();
        this.StudentThermalTicket = identification.getStudentThermalTicket();
        this.PensionerThermalTicket = identification.getPensionerThermalTicket();
        this.PremiumTicket = identification.getPremiumTicket();
        this.AquaParkTicket = identification.getAquaParkTicket();
        this.Sauna = identification.getSauna();
        this.SafeDeposit = identification.getSafeDeposit();
        this.Lounger = identification.getLounger();
        this.SunBed = identification.getSunBed();
        this.Baldachin = identification.getBaldachin();
        this.SunBedAtBeach = identification.getSunBedAtTheBeach();
        this.Balance.setValue(identification.getMoney());
    }

    //Listázza a personId-hoz tartózó szolgáltatások darabszámát szolgáltatásnév:darabszám szerint egy listába, de csak azokat aminek nem 0 a darabszáma
    //Visszatérési értéke egy lista
    public List<String> listServicesInfo()
    {
        List<String> infoList = new ArrayList<String>();
        infoList.add("Szauna:" + getSauna());
        infoList.add("Értékmegőrző:" + getSafeDeposit());
        infoList.add("Pihenőágy:" + getLounger());
        infoList.add("Napozóágy:" + getSunBed());
        infoList.add("Napozóágy a tengerparton:" + getSunBedAtBeach());
        infoList.add("Baldachin a tengerparton:" + getBaldachin());
        infoList.add("Szekrény:" + getLocker());
        List<String> removedList = removeZeroValue(infoList);
        return removedList;
    }

    //Listázza a personId-hoz tartozó jegyek darabszámát jegynév:darabszám szerint egy listába
    //Visszatérési értéke egy lista
    public List<String> listTicketInfos()
    {
        List<String> infoList = new ArrayList<String>();
        infoList.add("Felnőtt élményfürdő belépő:" + getAdultFellingTicket());
        infoList.add("Diák élményfürdő belépő:" + getStudentFellingTicket());
        infoList.add("Nyugdijas élményfürdő belépő:" + getFeelingPensionerTicket());
        infoList.add("Diák belépő:" + getStudentBeachTicket());
        infoList.add("Felnőtt belépő:" + getAdultBeachTicket());
        infoList.add("Nyugdíjas belépő:" + getPensionerBeachTicket());
        infoList.add("Diák Thermal belépő:" + getStudentThermalTicket());
        infoList.add("Felnőtt Thermal belépő:" + getAdultThermalTicket());
        infoList.add("Nyugdíjas Thermal belépő:" + getPensionerBeachTicket());
        infoList.add("Aquapark belépő:" +  getAquaParkTicket());
        infoList.add("Prémium belépőjegy:" + getPremiumTicket());
        return infoList;
    }

    //Nem kell paraméter, a personId-hoz tartozó jegyek egy olyan listáját adja vissza amiben csak azok a jegyek szerepelnek aminek a darabszáma nem 0
    public List<String> listTicketsWithoutZeroValues()
    {
        List<String> list = listTicketInfos();
        List<String> removed = removeZeroValue(list);
        return removed;
    }


    //A paraméterül kapott listából kitörli azokat az értéket amiben a kettőspont után 0 érték van
    //Visszatérési értéke a modosított lista
    private List<String> removeZeroValue(List<String> list)
    {
        List<String> newList = new ArrayList<>();
        for(String element: list)
        {
            String[] info = element.split(":");
            if(!(info[1].equals("0")))
            {
                newList.add(element);
            }
        }
        return newList;
    }
}