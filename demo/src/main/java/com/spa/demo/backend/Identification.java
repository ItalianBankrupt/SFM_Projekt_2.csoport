package com.spa.demo.backend;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Identification {
    //----------Kapcsolat a Reg. táblával---
    @ManyToOne
    @JoinColumn( name = "registration_GeneratedId")
    private Registration registration;
    //----------Egyedi azonosító------------
    @Id
    private String PersonId;
    //----------Jegyek----------------------
    private int AdultFellingTicket;
    private int StudentFellingTicket;
    private int FeelingPensionerTicket;
    private int AdultBeachTicket;
    private int StudentBeachTicket;
    private int PensionerBeachTicket;
    private int AdultThermalTicket;
    private int StudentThermalTicket;
    private int PensionerThermalTicket;
    private int AquaParkTicket;
    private int PremiumTicket;
    //----------Szolgáltatások--------------
    private int Sauna;
    private int SafeDeposit;
    private int Lounger;
    private int SunBed;
    private int SunBedAtTheBeach;
    private int Baldachin;
    private int Locker;
}
