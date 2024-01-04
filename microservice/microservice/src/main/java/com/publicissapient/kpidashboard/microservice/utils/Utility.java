package com.publicissapient.kpidashboard.microservice.utils;


import com.publicissapient.kpidashboard.microservice.exception.InternalServerErrorException;
import com.publicissapient.kpidashboard.microservice.exception.NotFoundException;
import com.publicissapient.kpidashboard.microservice.exception.ServiceUnavailableException;
import com.publicissapient.kpidashboard.microservice.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

public class Utility {

    private static final Logger logger = LoggerFactory.getLogger(Utility.class);

    public static <T> Mono<T> handleResponse(Mono<ClientResponse> responseMono, Class<T> responseType) {
        return responseMono.flatMap(response -> {
            HttpStatus httpStatus = response.statusCode();

            if (httpStatus.is2xxSuccessful()) {
                return response.bodyToMono(responseType);
            } else if (httpStatus == HttpStatus.UNAUTHORIZED) {
                return Mono.error(new UnauthorizedException("Unauthorized request."));
            } else if (httpStatus == HttpStatus.NOT_FOUND) {
                return Mono.error(new NotFoundException("Resource not found."));
            } else if (httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) {
                return Mono.error(new InternalServerErrorException("Internal server error."));
            } else {
                return Mono.error(new RuntimeException("Unexpected HTTP status: " + httpStatus));
            }
        });
    }

    public static RuntimeException handleFailure(Exception e) {
        if (e instanceof WebClientResponseException) {
            WebClientResponseException webClientException = (WebClientResponseException) e;
            HttpStatus httpStatus = webClientException.getStatusCode();
            String responseBody = webClientException.getResponseBodyAsString();

            if (httpStatus == HttpStatus.UNAUTHORIZED) {
                throw new UnauthorizedException("Unauthorized request.");
            } else if (httpStatus == HttpStatus.NOT_FOUND) {
                throw new NotFoundException("Resource not found.");
            } else if (httpStatus == HttpStatus.INTERNAL_SERVER_ERROR) {
                throw new InternalServerErrorException("Internal server error.");
            } else {
                throw new RuntimeException("Unexpected HTTP status: " + httpStatus);
            }

        } else if (e instanceof WebClientRequestException) {
            WebClientRequestException webClientException = (WebClientRequestException) e;
            logger.error("Error  occurred: " + e.getMessage());
            throw new ServiceUnavailableException("Service Unavailable" + e.getMessage());
        } else {
            logger.error("Unexpected exception occurred: " + e.getMessage());
            throw new RuntimeException("Error in service");
        }

    }


}
