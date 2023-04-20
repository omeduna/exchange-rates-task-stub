package com.shipmonk.testingday.service;

import com.shipmonk.testingday.integration.FixerClient;
import com.shipmonk.testingday.model.FixerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FixerService {

    private final FixerClient client;

    @Autowired
    public FixerService(FixerClient client) {
        this.client = client;
    }

    public FixerDTO getFixerExchangeRates(LocalDate date) {
        return client.getFixerExchangerRates(date);
    }

}
