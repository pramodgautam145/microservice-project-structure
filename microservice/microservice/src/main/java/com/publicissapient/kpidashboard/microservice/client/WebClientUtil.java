package com.publicissapient.kpidashboard.microservice.client;

import com.publicissapient.kpidashboard.microservice.service.AppServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Component
public class WebClientUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebClientUtil.class);
    private WebClient webClient;

    public WebClientUtil(WebClient webClient) {
        this.webClient = webClient;
    }

    public <T> Mono<T> get(String uri, Class<T> responseType) {
        logger.info("application url : {}",uri);
        return webClient.method(HttpMethod.GET)
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType);
    }

    public Mono<ClientResponse> getWebClientCall(String uri) {
        return webClient.method(HttpMethod.GET)
                .uri(uri)
                .exchangeToMono(clientResponse -> Mono.just(clientResponse));
    }

    public <T> Mono<T> get(String uri, Class<T> responseType, String pathVariable) {
        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path(uri).build(pathVariable))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> get(String uri, Class<T> responseType, String pathVariable, Map<String, String> requestParams, HttpHeaders headers) {
        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> {
                    uriBuilder = uriBuilder.path(uri);
                    for (Map.Entry<String, String> entry : requestParams.entrySet()) {
                        uriBuilder = uriBuilder.queryParam(entry.getKey(), entry.getValue());
                    }
                    return uriBuilder.build(pathVariable);
                })
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> List<T> getAll(String path, Class<T> responseType) {
        logger.info("application url : {}",path);
        Flux<T> response = webClient
                .method(HttpMethod.GET)
                .uri(path)
                .retrieve()
                .bodyToFlux(responseType);

        return response.collectList().block();
    }
    public <T, B> Mono<T> post(String uri, B body, Class<T> responseType) {
        return webClient.method(HttpMethod.POST)
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T, B> Mono<T> put(String uri, B body, Class<T> responseType) {
        return webClient.method(HttpMethod.PUT)
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(responseType);
    }

    public <T> Mono<T> delete(String uri, Class<T> responseType) {
        return webClient.method(HttpMethod.DELETE)
                .uri(uri)
                .retrieve()
                .bodyToMono(responseType);
    }
}
