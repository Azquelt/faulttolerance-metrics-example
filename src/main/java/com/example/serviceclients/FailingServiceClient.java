package com.example.serviceclients;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FailingServiceClient {

    public String call() {
        throw new RuntimeException("Failing Service failed!");
    }
    
}
