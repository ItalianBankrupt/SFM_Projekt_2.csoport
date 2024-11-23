package com.spa.demo.backend;


import com.spa.demo.SpringManager;
import com.spa.demo.frontend.Cassa.Models.Buyer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ConfigurableApplicationContext;
import com.spa.demo.frontend.Cassa.Models.Buyer;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RegistrationRepositoryTest {
    @Autowired
    private RegistrationRepository registrationRepository;

    private Buyer buyer;

    @Test
    void testSave(){
        buyer = new Buyer("123456FA","Teszt Elek","Debrecen","Laktanya utca 6","4200",1);
        buyer.GenerateId(buyer.getStatus());

        Registration registration = new Registration();
        registration.setName(buyer.getName());
        registration.setCity(buyer.getCity());
        registration.setStreet(buyer.getStreet());
        registration.setPostCode(buyer.getPostCode());
        registration.setGeneratedId(buyer.getId());

        registrationRepository.save(registration);
        List<Registration> registrations = registrationRepository.findByName(registration.getName());
        assertEquals(1, registrations.size());
        assertEquals(registration, registrations.get(0));
        assertEquals("Teszt Elek", registrations.get(0).getName());
        assertEquals("Debrecen", registrations.get(0).getCity());
        assertEquals("Laktanya utca 6", registrations.get(0).getStreet());
        assertEquals("4200", registrations.get(0).getPostCode());
        assertEquals("123456FA", registrations.get(0).getIDNumber());
    }
}
