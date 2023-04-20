package com.shipmonk.testingday.controller;

import com.shipmonk.testingday.manager.ExchangeRatesManager;
import com.shipmonk.testingday.model.ExchangeRatesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Currency;

@RestController
@RequestMapping(
    path = "/api/v1/rates"
)
@Slf4j
public class ExchangeRatesController {

    private static final Currency DEFAULT_BASE = Currency.getInstance("USD");

    private final ExchangeRatesManager exchangeRatesManager;

    @Autowired
    public ExchangeRatesController(ExchangeRatesManager exchangeRatesManager) {
        this.exchangeRatesManager = exchangeRatesManager;
    }

    @GetMapping("/{day}")
    public ResponseEntity<ExchangeRatesDTO> getRates(@PathVariable("day") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        ExchangeRatesDTO response = exchangeRatesManager.getRates(DEFAULT_BASE, date);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
