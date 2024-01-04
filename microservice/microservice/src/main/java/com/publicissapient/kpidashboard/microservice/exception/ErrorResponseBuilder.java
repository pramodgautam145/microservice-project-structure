package com.publicissapient.kpidashboard.microservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public class ErrorResponseBuilder {

    public static ErrorResponse build(HttpStatus status, Exception exception, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(LocalDateTime.now());
        errorResponse.setStatus(status.value());
        errorResponse.setError(status.getReasonPhrase());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURI());

        return errorResponse;
    }
}
