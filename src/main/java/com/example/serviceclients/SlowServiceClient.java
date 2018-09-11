package com.example.serviceclients;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SlowServiceClient {
    
    private Random r = new Random();
    
    public String call() {
        while (r.nextFloat() > 0.1f) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                return "Interrupted";
            }
        }
        return "OK";
    }

}
