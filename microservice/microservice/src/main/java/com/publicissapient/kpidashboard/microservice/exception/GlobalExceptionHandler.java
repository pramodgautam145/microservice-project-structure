package com.publicissapient.kpidashboard.microservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponseBuilder.build(HttpStatus.INTERNAL_SERVER_ERROR, exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponseBuilder.build(HttpStatus.BAD_REQUEST, exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponseBuilder.build(HttpStatus.SERVICE_UNAVAILABLE, exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(value = InternalServerErrorException.class)
    public ResponseEntity<Object> handleServiceInternalServerException(InternalServerErrorException exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponseBuilder.build(HttpStatus.INTERNAL_SERVER_ERROR, exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> handleServiceUnauthorizedException(UnauthorizedException exception, WebRequest request) {
        ErrorResponse errorResponse = ErrorResponseBuilder.build(HttpStatus.UNAUTHORIZED, exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
