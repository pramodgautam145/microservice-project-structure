package com.publicissapient.kpidashboard.microservice.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestClient {
   private final String baseUrl = null;
    private final RestTemplate restTemplate;

    public RestClient() {
       // this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
    }

    // Create
    public <T> T create(String path, T entity) {
        String url = baseUrl + path;
        ResponseEntity<T> response = restTemplate.postForEntity(url, entity, (Class<T>) entity.getClass());
        return response.getBody();
    }

    // Read
    public <T> T getById(String path, Class<T> responseType, Object... uriVariables) {
        String url = path;
        return restTemplate.getForObject(url, responseType);
    }

    public <T> List<T> getAll(String path, Class<T> responseType) {
        String url = baseUrl + path;
        ResponseEntity<List<T>> response = restTemplate.exchange(url, HttpMethod.GET, null,
                ParameterizedTypeReference.forType(List.class));
        return response.getBody();
    }

    // Update
    public <T> void update(String path, T entity, Object... uriVariables) {
        String url = baseUrl + path;
        restTemplate.put(url, entity, uriVariables);
    }

    // Delete
    public void delete(String path, Object... uriVariables) {
        String url = baseUrl + path;
        restTemplate.delete(url, uriVariables);
    }
}
