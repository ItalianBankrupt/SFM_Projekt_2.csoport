package com.spa.demo.backend;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RegistrationRepositoryTest {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Test
    void testSave(){
        Registration registration = Registration.builder()
                .Name("Teszt Elek")
                .City("Debrecen")
                .Street("Laktanya utca")
                .PostCode("4000")
                .IDNumber("123456FA")
                .CostumerType(1)
                .build();
        registrationRepository.save(registration);

        List<Registration> registrations = registrationRepository.findByName("Teszt Elek");
        assertEquals(1, registrations.size());
        assertEquals(registration, registrations.get(0));
        assertEquals("Teszt Elek", registrations.get(0).getName());
        assertEquals("Debrecen", registrations.get(0).getCity());
        assertEquals("Laktanya utca", registrations.get(0).getStreet());
        assertEquals("4000", registrations.get(0).getPostCode());
        assertEquals("123456FA", registrations.get(0).getIDNumber());
    }
}
