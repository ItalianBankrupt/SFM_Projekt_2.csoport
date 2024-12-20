package com.spa.demo.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ServicesRepositoryTest {

    @Autowired
    private ServicesRepository servicesRepository;

    @Test
    void testFindByName() {
        Services services = new Services();
        services.setName("Teszt belepo");
        services.setPrice(100);
        services.setType("Belepo");
        services.setId(1);
        servicesRepository.save(services);

        List<Services> test = servicesRepository.findByName("Teszt belepo");

        assertFalse(test.isEmpty());
        assertEquals(1, test.size());
        Services foundService = test.get(0);
        assertEquals("Teszt belepo", foundService.getName());
        assertEquals(100, foundService.getPrice());
        assertEquals("Belepo", foundService.getType());
    }


}
