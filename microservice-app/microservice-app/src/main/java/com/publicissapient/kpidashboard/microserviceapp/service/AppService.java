package com.publicissapient.kpidashboard.microserviceapp.service;

import com.publicissapient.kpidashboard.microserviceapp.dao.AppRepository;
import com.publicissapient.kpidashboard.microserviceapp.model.Demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {
   @Autowired
   private AppRepository appRepository;
   private static final Logger logger = LoggerFactory.getLogger(AppService.class);
   public List<Demo> getAppServiceData(){
      logger.info("request recieved app service data");
      return appRepository.findAll();
   }

   public Demo saveData(Demo demo){
      logger.info("request recieved app service data");
     return appRepository.save(demo);
   }
}
