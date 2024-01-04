package com.publicissapient.kpidashboard.microserviceapp.dao;

import com.publicissapient.kpidashboard.microserviceapp.model.Demo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppRepository extends MongoRepository<Demo, String> {

}
