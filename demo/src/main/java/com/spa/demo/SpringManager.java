package com.spa.demo;

import com.spa.demo.backend.Services;
import com.spa.demo.backend.ServicesRepository;
import com.spa.demo.frontend.Manager;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class SpringManager implements Manager {
    private ConfigurableApplicationContext context;

    @Override
    public void startBackend() {
        context = SpringApplication.run(SpaApplication.class);
    }

    @Override
    public void stopBackend() {
        context.stop();
    }

    @Override
    public String getItemName() {
        ServicesRepository repo = context.getBean(ServicesRepository.class);
        return repo.findByName("Test").get(0).getName();
    }

    @Override
    public List<String> getItemType(String type) {
        ServicesRepository repo = context.getBean(ServicesRepository.class);
        return repo.findByType(type).stream().map(Services::getName).collect(Collectors.toList());
    }


}
