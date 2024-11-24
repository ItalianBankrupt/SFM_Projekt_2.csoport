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
        test();
    }

    public static  ConfigurableApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void stopBackend() {
        context.stop();
    }

    public String getItemName() {
        ServicesRepository repo = context.getBean(ServicesRepository.class);
        return repo.findByName("Test").get(0).getName();
    }

    public List<String> getServicesItemType(String type) {
        ServicesRepository servRepo = context.getBean(ServicesRepository.class);
        return servRepo.findByType(type).stream().map(Services::getName).collect(Collectors.toList());
    }

    public List<String> getRestaurantItemType(String type) {
        RestaurantRepository restRepo = context.getBean(RestaurantRepository.class);
        return restRepo.findByType(type).stream().map(Restaurant::getName).collect(Collectors.toList());
    }

    private void test()
    {
        IdentificationRepository repo = context.getBean(IdentificationRepository.class);
        Identification identification = Identification.builder().PremiumTicket(1).SunBedAtTheBeach(1).Lounger(1).StudentFellingTicket(0).PensionerThermalTicket(0).money(0).personId("alma").build();
        repo.save(identification);
        Identification identificatio1n = Identification.builder().PremiumTicket(1).SunBedAtTheBeach(0).Lounger(0).StudentFellingTicket(0).PensionerThermalTicket(1).money(0).personId("körte").build();
        repo.save(identificatio1n);
    }

}
