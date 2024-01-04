package com.publicissapient.kpidashboard.microservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.publicissapient.kpidashboard.microservice.client.WebClientUtil;
import com.publicissapient.kpidashboard.microservice.config.AppConfig;
import com.publicissapient.kpidashboard.microservice.constant.CommonConstant;
import com.publicissapient.kpidashboard.microservice.model.Demo;
import com.publicissapient.kpidashboard.microservice.utils.Utility;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AppServiceImpl implements AppService {

    private static final Logger logger = LoggerFactory.getLogger(AppServiceImpl.class);

    private WebClientUtil webClientUtil;

    @Autowired
    private AppConfig appConfig;

    @Autowired
    public AppServiceImpl(WebClientUtil webClientUtil) {
        this.webClientUtil = webClientUtil;
    }

    @CircuitBreaker(name = "BACKEND_A", fallbackMethod = "fallbackForCallingAppService")
    public List<Demo> callingAppService() {
        logger.info("request recieved at service level");
        List<Demo> res = webClientUtil.getAll(appConfig.getBaseUrl() + CommonConstant.url, Demo.class);
        if (res != null) {
            logger.info("api response {} ",res);
                return res;
            }
        return null;
    }

    public List<Demo> fallbackForCallingAppService(Exception e) {
        logger.error("Error occurred: {} " + e.getMessage());
        Utility.handleFailure(e);
        return null;
    }

}
