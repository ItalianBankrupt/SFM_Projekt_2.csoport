package com.spa.demo.backend;

import com.spa.demo.frontend.Cassa.Models.Buyer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class IdentificationRepositoryTest {
    @Autowired
    private IdentificationRepository identificationRepository;

    @Autowired
    private RegistrationRepository registrationRepository;
    private Buyer buyer;

    @Test
    void testSave()
    {
        buyer = new Buyer("123456FA","Teszt Elek","Debrecen","Laktanya utca 6","4200",1);
        buyer.GenerateId(buyer.getStatus());

        Registration registration = Registration.builder()
                .IDNumber(buyer.getId())
                .GeneratedId("FE1123123456FA2126A")
                .CostumerType(buyer.getStatus())
                .PostCode(buyer.getPostCode())
                .Street(buyer.getStreet())
                .City(buyer.getCity())
                .name(buyer.getName())
                .build();

        registrationRepository.save(registration);

        Identification identificationAdult = Identification.builder()
                .registration(registration)
                .personId("FE1123123456FA2126B")
                .money(5000)
                .AdultBeachTicket(1)
                .AdultFellingTicket(2)
                .AdultThermalTicket(3)
                .Lounger(4)
                .Sauna(3)
                .SafeDeposit(1)
                .Baldachin(2)
                .build();

        Identification identificationPensioner =Identification.builder()
                .registration(registration)
                .personId("FE1123123456FA2126C")
                .money(0)
                .PensionerBeachTicket(2)
                .PensionerThermalTicket(1)
                .Sauna(1)
                .build();

        Identification identificationStudent = Identification.builder()
                .registration(registration)
                .personId("FE1123123456FA2126D")
                .money(1200)
                .StudentBeachTicket(1)
                .StudentFellingTicket(3)
                .StudentThermalTicket(4)
                .PremiumTicket(1)
                .build();

        identificationRepository.save(identificationAdult);
        identificationRepository.save(identificationPensioner);
        identificationRepository.save(identificationStudent);

        List<Identification> identifications = identificationRepository.findAll();
        assertEquals(3, identifications.size());
        assertEquals("FE1123123456FA2126B", identifications.get(0).getPersonId());
        assertEquals(registration, identifications.get(0).getRegistration());



    }
}
