package com.spa.demo.frontend.Cassa.addcustomerControllers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCustomerControllerTest {
    @Test
    void testCheckInfos()
    {
        String postCode1 = "1234";
        String postCode2 = "56784";
        String postCode3 = "125";
        String Id1 = "123456FE";
        String Id2 = "123456FFe";
        String Id3 = "123456F";

        AddCustomerController addCustomerController = new AddCustomerController();
        assertEquals("none", addCustomerController.checkInfos(postCode1, Id1));
        assertEquals("Személyigazolványszám", addCustomerController.checkInfos(postCode1, Id2));
        assertEquals("Személyigazolványszám", addCustomerController.checkInfos(postCode1, Id3));
        assertEquals("Irányítószám", addCustomerController.checkInfos(postCode2, Id1));
        assertEquals("Irányítószám", addCustomerController.checkInfos(postCode3, Id1));
        assertEquals("Irányítószám és személyigazolványszám", addCustomerController.checkInfos(postCode2, Id2));
    }
}
