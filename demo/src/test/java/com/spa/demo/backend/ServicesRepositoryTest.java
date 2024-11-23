package com.spa.demo.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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

        List<Services> found = servicesRepository.findByName("Teszt belepo");

    }


}
