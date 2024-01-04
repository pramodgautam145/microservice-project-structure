package com.publicissapient.kpidashboard.microserviceapp.controller;

import com.publicissapient.kpidashboard.microserviceapp.model.Demo;
import com.publicissapient.kpidashboard.microserviceapp.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class AppController {
    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    private AppService appService;

    @GetMapping("/getData")
    public List<Demo> getAppData() {
        logger.info("request received app controller");
        logger.info("Trace ID: {}", MDC.get("traceId"));
        logger.info("Span ID: {}", MDC.get("spanId"));
        return appService.getAppServiceData();
    }

    @GetMapping("/saveData")
    public Demo getSaveData(@RequestBody Demo demo) {
        logger.info("request received app controller");
        logger.info("Trace ID: {}", MDC.get("traceId"));
        logger.info("Span ID: {}", MDC.get("spanId"));
        return appService.saveData(demo);
    }
}
