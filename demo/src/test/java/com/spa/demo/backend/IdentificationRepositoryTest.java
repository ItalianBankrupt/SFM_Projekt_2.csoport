package com.spa.demo.backend;

import com.spa.demo.frontend.Cassa.Models.Buyer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
public class IdentificationRepositoryTest {
    @Autowired
    private IdentificationRepository identificationRepository;

    private Buyer buyer;

    @Test
    void testSave()
    {

    }
}
