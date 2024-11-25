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
    }

    public static  ConfigurableApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void stopBackend() {
        context.close();
    }
}
