package com.spa.demo.frontend.Cassa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private int StudendAndChildTicket = 0;
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
}
