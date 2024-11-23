package com.spa.demo.backend;


import com.spa.demo.SpringManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class IdentificationRepositoryTest {
    private ConfigurableApplicationContext context;
    private IdentificationRepository identificationRepository;
    private RegistrationRepository registrationRepository;


    @Test
    void testSave(){
        context = SpringManager.getApplicationContext();
        identificationRepository = context.getBean(IdentificationRepository.class);
        registrationRepository = context.getBean(RegistrationRepository.class);

        Identification identification = new Identification();
        identification.setBaldachin(1);
        identification.setLounger(1);
        identification.setMoney(1000);
        identification.setPensionerBeachTicket(1);
        identificationRepository.save(identification);

        List<Identification> identifications = identificationRepository.findAll();
        assertEquals(1, identifications.size());
        Identification identification1 = identifications.get(0);
        assertEquals(identification.getBaldachin(), identification1.getBaldachin());
        assertEquals(identification.getLounger(), identification1.getLounger());
        assertEquals(identification.getMoney(), identification1.getMoney());

    }
}
