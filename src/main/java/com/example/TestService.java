package com.example;

import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import com.example.serviceclients.BreakingServiceClient;
import com.example.serviceclients.ConstrainedServiceClient;
import com.example.serviceclients.FailingServiceClient;
import com.example.serviceclients.SlowServiceClient;
import com.example.serviceclients.UnreliableServiceClient;

@ApplicationScoped
@Path("/")
public class TestService {
    
    int wibble = 0;
    
    @Inject
    private UnreliableServiceClient unreliableServiceClient;
    
    @Inject
    private ConstrainedServiceClient constrainedServiceClient;
    
    @Inject
    private FailingServiceClient failingServiceClient;
    
    @Inject
    private BreakingServiceClient breakingServiceClient;
    
    @Inject
    private SlowServiceClient slowServiceClient;
    
    @Produces(MediaType.TEXT_HTML)
    @GET
    public InputStream showIndex() {
        return TestService.class.getResourceAsStream("/index.html");
    }
    
    @Path("callUnreliableService")
    @GET
    @Retry
    public String callUnreliableService() {
        return unreliableServiceClient.call();
    }
    
    @Path("callConstrainedService")
    @GET
    @Bulkhead(3)
    public String callConstrainedService() {
        return constrainedServiceClient.call();
    }
    
    @Path("callFailingService")
    @GET
    @Fallback(fallbackMethod = "failingServiceFallback")
    public String callFailingService() {
        return failingServiceClient.call();
    }
    
    public String failingServiceFallback() {
        return "FALLBACK";
    }
    
    @Path("callBreakingService")
    @GET
    @CircuitBreaker
    public String callBreakingService() {
        return breakingServiceClient.call();
    }
    
    @Path("callSlowService")
    @GET
    @Timeout(1000)
    public String callSlowService() {
        return slowServiceClient.call();
    }
    
}
