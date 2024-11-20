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
    @Id
    private String BuyerId;                     // Kapcsolat a Registration tábla GeneratedId rekordjával
    private String PersonId;                    //
    private int ExperienceAdultTicket;
    private int ExperienceStudentTicket;
    private int ExperiencePensionerTicket;
    private int MedicalAdultTicket;
    private int MedicalStudentTicket;
    private int MedicalPensionerTicket;
    private int OutsideAdultTicket;
    private int OutsideStudentTicket;
    private int OutsidePensionerTicket;
    private int Sauna;
    private int SafeDeposit;
    private int Lounger;
    private int SunBed;
    private int SunBedAtTheBeach;
    private int Baldachin;
    private int AquaParkTicket;
    private int PremiumTicket;
    private int Locker;
}
