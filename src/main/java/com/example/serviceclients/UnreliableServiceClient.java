package com.example.serviceclients;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

/**
 * A dummy client for an unreliable service
 */
@ApplicationScoped
public class UnreliableServiceClient {

    private Random r = new Random();
    
    public String call() {
        if (r.nextFloat() < 0.1) {
            throw new RuntimeException("Unreliable Service failed!");
        }
        return "OK";
    }
}
