package com.shipmonk.testingday.manager;

import com.shipmonk.testingday.entity.ExchangeRate;
import com.shipmonk.testingday.error.ExchangeRateNotFoundException;
import com.shipmonk.testingday.model.ExchangeRatesDTO;
import com.shipmonk.testingday.model.FixerDTO;
import com.shipmonk.testingday.service.ExchangeRatesService;
import com.shipmonk.testingday.service.FixerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

@Service
public class ExchangeRatesManager {

    private final ExchangeRatesService exchangeRatesService;
    private final FixerService fixerService;

    @Autowired
    public ExchangeRatesManager(ExchangeRatesService exchangeRatesService, FixerService fixerService) {
        this.exchangeRatesService = exchangeRatesService;
        this.fixerService = fixerService;
    }

    public ExchangeRatesDTO getRates(Currency currency, LocalDate date) {
        List<ExchangeRate> exchangeRates = this.exchangeRatesService.getRates(currency, date);
        if (exchangeRates.isEmpty()) {
            FixerDTO fixer = this.fixerService.getFixerExchangeRates(date);
            if (fixer != null) {
                exchangeRatesService.saveRates(fixer);
                exchangeRates = this.exchangeRatesService.getRates(currency, date);
                if (!exchangeRates.isEmpty()) {
                    return new ExchangeRatesDTO(exchangeRates);
                } else {
                    throw new ExchangeRateNotFoundException();
                }
            } else {
                throw new ExchangeRateNotFoundException();
            }
        } else {
            return new ExchangeRatesDTO(exchangeRates);
        }
    }

}
