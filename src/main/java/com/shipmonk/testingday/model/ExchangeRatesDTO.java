package com.shipmonk.testingday.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.shipmonk.testingday.entity.ExchangeRate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class ExchangeRatesDTO {

    private boolean success;
    private Instant timestamp;
    private Currency base;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;
    private Map<Currency, BigDecimal> rates = new HashMap<>();

    public ExchangeRatesDTO(List<ExchangeRate> exchangeRates) {
        this.success = true;
        this.timestamp = Instant.now();
        this.base = exchangeRates.get(0).getBase();
        this.date = exchangeRates.get(0).getDate();
        this.rates = exchangeRates.stream().collect(Collectors.toMap(ExchangeRate::getCurrency, ExchangeRate::getRate));
    }

}
