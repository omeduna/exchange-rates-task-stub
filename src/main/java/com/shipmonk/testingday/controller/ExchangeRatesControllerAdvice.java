package com.shipmonk.testingday.controller;

import com.shipmonk.testingday.error.ExchangeRateNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExchangeRatesControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ExchangeRateNotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Exchange rate not found", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Illegal argument", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGeneric(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Server error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
