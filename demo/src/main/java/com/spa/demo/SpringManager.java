package com.spa.demo;

import com.spa.demo.backend.*;
import com.spa.demo.frontend.Manager;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class SpringManager implements Manager {
    private static ConfigurableApplicationContext context;

    @Override
    public void startBackend() {
        context = SpringApplication.run(SpaApplication.class);
       // test();
    }

    public static  ConfigurableApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void stopBackend() {
        context.close();
    }
  /*  public static void test() {
        Registration reg=Registration.builder().City("asd").name("asd").Street("asd").PostCode("1232").GeneratedId("alma").build();
        RegistrationRepository regRepo=context.getBean(RegistrationRepository.class);
        regRepo.save(reg);
        Identification identification=Identification.builder().personId("korte").money(0).registration(reg).build();
        IdentificationRepository idRepo=context.getBean(IdentificationRepository.class);
        idRepo.save(identification);

    }*/
}
