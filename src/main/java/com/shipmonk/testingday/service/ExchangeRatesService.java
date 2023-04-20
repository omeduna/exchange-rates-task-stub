package com.shipmonk.testingday.service;

import com.shipmonk.testingday.entity.ExchangeRate;
import com.shipmonk.testingday.model.FixerDTO;
import com.shipmonk.testingday.repository.ExchangeRatesRepository;
import com.shipmonk.testingday.utils.FixerToExchangeRatesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Service
public class ExchangeRatesService {

    private final ExchangeRatesRepository repository;

    @Autowired
    public ExchangeRatesService(ExchangeRatesRepository repository) {
        this.repository = repository;
    }

    public List<ExchangeRate> getRates(Currency currency, LocalDate date) {
        return this.repository.findExchangeRateByBaseAndDate(currency, date);
    }

    public void saveRates(FixerDTO fixer) {
        repository.saveAll(FixerToExchangeRatesMapper.toExchangeRate(fixer));
    }

}
