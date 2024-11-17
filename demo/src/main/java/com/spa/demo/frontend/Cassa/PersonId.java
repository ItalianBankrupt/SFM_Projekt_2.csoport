package com.spa.demo.frontend.Cassa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class PersonId
{
    //Infos from buyerClass
    private String id;
    private String buyerId;

    //Tickets and Services 0-if doesn't have that type 1-if have
    private int AdultFelling = 0;
    private int StudentFelling = 0;
    private int ChildTicket = 0;
    private int BeachTicket = 0;
    private int RetiredTicket = 0;
    private int StudentAndChildTicket = 0;
    private int Sauna = 0;
    private int SafeDeposit = 0;
    private int Lounger = 0;
    private int SunBed = 0;
    private int SunBedOnBeach = 0;
    private int Baldachin = 0;
    private int AquaParkMinimal = 0;
    private int AquaParkNormal = 0;
    private int PremiumTicket = 0;
    private int Locker = 0;

    //Balance info
    private int Balance = 0;

    public PersonId(String id,String buyerId)
    {
        this.id = id;
        this.buyerId = buyerId;
    }

    public List<String> listServicesInfo()
    {
        List<String> infoList = new ArrayList<String>();
        infoList.add("Szauna:" + getSauna());
        infoList.add("Értékmegőrző:" + getSafeDeposit());
        infoList.add("Pihenőágy:" + getLounger());
        infoList.add("Napozóágy:" + getSunBed());
        infoList.add("Napozóágy a tengerparton:" + getSunBedOnBeach());
        infoList.add("Baldachin a tengerparton:" + getBaldachin());
        infoList.add("Szekrény:" + getLocker());
        List<String> removedList = removeZeroValue(infoList);
        return removedList;
    }

    public List<String> listTicketInfos()
    {
        List<String> infoList = new ArrayList<String>();
        infoList.add("Felnőtt élményfürdő belépő:" + getAdultFelling());
        infoList.add("Diák élményfürdő belépő:" + getStudentFelling());
        infoList.add("Gyermek belépő:" + getChildTicket());
        infoList.add("Fürdő belépő:" + getBeachTicket());
        infoList.add("Nyugdíjas belépő:" + getRetiredTicket());
        infoList.add("Diák/Gyermek belépő:" + getStudentAndChildTicket());
        infoList.add("Aquapark normal belépő:" + getAquaParkNormal());
        infoList.add("Aquapark minimal belépő:" + getAquaParkMinimal());
        infoList.add("Prémium belépőjegy:" + getPremiumTicket());
        List<String> removedList = removeZeroValue(infoList);
        return removedList;
    }

    private List<String> removeZeroValue(List<String> list)
    {
        List<String> newList = new ArrayList<>();
        for(String element: list)
        {
            String[] info = element.split(":");
            if(info[1].equals("1"))
            {
                newList.add(element);
            }
        }
        return newList;
    }
}
