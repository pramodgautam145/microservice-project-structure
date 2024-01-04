package com.publicissapient.kpidashboard.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableEurekaClient
public class MicroserviceApplication {
	private static final Logger logger = LoggerFactory.getLogger(MicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
		logger.info("BaseUrl: {}", System.getProperty("myapp.baseUrl"));
	}

}
