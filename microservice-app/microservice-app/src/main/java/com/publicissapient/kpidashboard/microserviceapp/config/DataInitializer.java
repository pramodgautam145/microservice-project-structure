package com.publicissapient.kpidashboard.microserviceapp.config;

import com.publicissapient.kpidashboard.microserviceapp.dao.AppRepository;
import com.publicissapient.kpidashboard.microserviceapp.model.Demo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final AppRepository appRepository;

    public DataInitializer(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Insert your data here
        Demo demo1 = new Demo();
        demo1.setTitle("first demo");
        demo1.setName("demo1 record");

        Demo demo2 = new Demo();
        demo2.setTitle("second demo");
        demo2.setName("demo2 record");

        appRepository.save(demo1);
        appRepository.save(demo2);

        System.out.println("Data inserted into MongoDB!");
    }
}
