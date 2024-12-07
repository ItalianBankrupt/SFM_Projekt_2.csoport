package com.spa.demo.frontend.Cassa.Models;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BuyerTest
{
    @Test
    void testGenerateId()
    {
        Buyer studentBuyer = new Buyer("123456FE", "Veres Attila", "ASd", "asd", "4352", 2);
        Buyer pensionerBuyer = new Buyer("123456FE", "Teszt Elek", "asdf", "sdfa", "4352", 3);

        studentBuyer.GenerateId(studentBuyer.getStatus());
        pensionerBuyer.GenerateId(pensionerBuyer.getStatus());

        assertEquals("DI",studentBuyer.getIds().get(0).substring(0,2) );
        assertEquals("NY",pensionerBuyer.getIds().get(0).substring(0,2) );

    }

    @Test
    void testDelete()
    {
        Buyer studentBuyer = new Buyer("123456FE", "Veres Attila", "ASd", "asd", "4352", 2);
        studentBuyer.GenerateId(studentBuyer.getStatus());
        studentBuyer.GenerateId(2);
        studentBuyer.GenerateId(3);
        int numberOfIds = studentBuyer.getIds().size();
        studentBuyer.RemoveId(studentBuyer.getIds().get(1));

        assertEquals(numberOfIds - 1, studentBuyer.getIds().size());
    }

    @Test
    void testUpdateList()
    {
        Buyer studentBuyer = new Buyer("123456FE", "Veres Attila", "ASd", "asd", "4352", 2);
        studentBuyer.GenerateId(studentBuyer.getStatus());
        studentBuyer.GenerateId(2);
        studentBuyer.GenerateId(3);
        studentBuyer.GenerateId(1);
        int numberOfIds = studentBuyer.getIds().size();

        studentBuyer.setId("123456DE");
        studentBuyer.UpdateList(numberOfIds, "All");
        assertEquals(numberOfIds, studentBuyer.getIds().size());
        assertTrue(studentBuyer.getIds().get(1).contains("123456DE"));

        studentBuyer.setStatus(1);
        studentBuyer.UpdateList(numberOfIds, "FirstOnly");
        assertTrue(studentBuyer.getIds().get(0).contains("FE"));
    }

    @Test
    void testAll()
    {
        Buyer newBuyer = new Buyer("","","","","",0);
        newBuyer.setName("Test");
        newBuyer.setCity("Valahol");
        newBuyer.setStreet("asd");
        newBuyer.setPostCode("3241");
        newBuyer.setNumberOfGeneratedId(newBuyer.getNumberOfGeneratedId());
        assertEquals("Test", newBuyer.getName());
        assertEquals("Valahol", newBuyer.getCity());
        assertEquals("asd", newBuyer.getStreet());
        assertEquals("3241", newBuyer.getPostCode());
    }

}
