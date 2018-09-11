package com.example.serviceclients;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class BreakingServiceClient {

    @Inject
    @ConfigProperty(name = "breaking.service.broken")
    private Provider<Boolean> serviceBroken;
    
    public String call() {
        if (serviceBroken.get()) {
            throw new RuntimeException("Breaking Service failed!");
        }
        return "OK";
    }
}
