package com.shipmonk.testingday.utils;

import com.shipmonk.testingday.entity.ExchangeRate;
import com.shipmonk.testingday.model.FixerDTO;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;

@Slf4j
public class FixerToExchangeRatesMapper {

    public static List<ExchangeRate> toExchangeRate(FixerDTO fixerDTO) {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : fixerDTO.getRates().entrySet()) {
            ExchangeRate er = new ExchangeRate();
            er.setBase(fixerDTO.getBase());
            try {
                er.setCurrency(Currency.getInstance(entry.getKey()));
            } catch (Exception e) {
                log.error("Unknown currency {}", entry.getKey());
                continue;
            }
            er.setDate(fixerDTO.getDate());
            er.setRate(entry.getValue());
            exchangeRates.add(er);
        }
        return exchangeRates;
    }

}
