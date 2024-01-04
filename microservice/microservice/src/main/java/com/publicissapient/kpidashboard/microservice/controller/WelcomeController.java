package com.publicissapient.kpidashboard.microservice.controller;


import com.publicissapient.kpidashboard.microservice.client.RestClient;
import com.publicissapient.kpidashboard.microservice.model.Demo;
import com.publicissapient.kpidashboard.microservice.service.AppService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@Slf4j
public class WelcomeController {

    @Autowired
    private AppService appService;
    @Autowired
    private RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Timed(value = "health.endpoint.time", description = "Time taken to execute my method")
    @GetMapping("/health")
    public Map<String, Object> health() {
        Map<String, Object> health = new HashMap<>();
        health.put("status", "UP");
        // Add pod name
        String podName = System.getenv("HOSTNAME");
        health.put("podName", podName);
        // Add server details
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            health.put("serverIp", localhost.getHostAddress());
            health.put("serverName", localhost.getHostName());
        } catch (java.net.UnknownHostException e) {
            health.put("serverError", e.getMessage());
        }
        return health;
    }
    @GetMapping("/services")
    public String welcome() {
        logger.info("welcome to Controller {}", "WelcomeController");
        logger.info("Trace ID: {}", MDC.get("traceId"));
        logger.info("Span ID: {}", MDC.get("spanId"));
        String podName = System.getenv("HOSTNAME");
        logger.info("Request handled by pod: {}", podName);
        return "Welcome to KPI Dashboard Microservice!";
    }
    @GetMapping("/retrieve")
    public List<Demo> retrieveDemoData() {
        logger.info("request recieve demo data");
        List<Demo> list = appService.callingAppService();
        return list;
    }

}
