package com.spa.demo.frontend.Cassa.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonIdTest {

    @Test
    void TestConstructor()
    {
        Buyer newBuyer = new Buyer("123456FE", "Test", "asd","asd","1234", 1);
        newBuyer.GenerateId(1);
        PersonId newPersonId = new PersonId(newBuyer.getId(), newBuyer.getId());
        assertEquals(newBuyer.getId(), newPersonId.getBuyerId());
        newPersonId.setId("123456Fe");
        assertEquals("123456Fe", newPersonId.getId());
        newPersonId.setStudentFellingTicket(0);
        newPersonId.setFeelingPensionerTicket(0);
        newPersonId.setAdultFellingTicket(1);
        newPersonId.setAdultBeachTicket(1);
        newPersonId.setAdultThermalTicket(1);
        newPersonId.setStudentBeachTicket(0);
        newPersonId.setStudentThermalTicket(0);
        newPersonId.setPensionerBeachTicket(0);
        newPersonId.setPensionerThermalTicket(0);
        newPersonId.setSauna(1);
        newPersonId.setSafeDeposit(1);
        newPersonId.setLocker(0);
        newPersonId.setLounger(0);
        newPersonId.setSunBed(0);
        newPersonId.setSunBedAtBeach(0);
        newPersonId.setBaldachin(0);
        newPersonId.setAquaParkTicket(0);
        newPersonId.setPremiumTicket(1);

        IntegerProperty balance = new SimpleIntegerProperty(3000);
        newPersonId.setBalance(balance);
        assertEquals(balance,newPersonId.getBalance());

        List<String> services = newPersonId.listServicesInfo();
        assertEquals(2,services.size());
        List<String> tickets = newPersonId.listTicketInfos();
        assertEquals(11,tickets.size());
        List<String> ticketsWithoutZeroValues = newPersonId.listTicketsWithoutZeroValues();
        assertEquals(4,ticketsWithoutZeroValues.size());
    }


}
