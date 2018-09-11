package com.example.serviceclients;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ConstrainedServiceClient {
    
    @Inject
    @ConfigProperty(name = "constrained.service.delay")
    private Provider<Integer> timeToDelay;

    public String call() {
        try {
            Thread.sleep(timeToDelay.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "OK";
    }
}
