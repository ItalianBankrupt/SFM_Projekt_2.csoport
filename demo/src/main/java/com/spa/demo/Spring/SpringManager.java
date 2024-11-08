package com.spa.demo.Spring;

import com.spa.demo.frontend.Manager;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

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

}
