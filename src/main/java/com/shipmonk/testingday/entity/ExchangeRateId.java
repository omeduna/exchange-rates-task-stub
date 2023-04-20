package com.shipmonk.testingday.entity;

import com.shipmonk.testingday.utils.CurrencyConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Currency;

public class ExchangeRateId implements Serializable {

    @Convert(converter = CurrencyConverter.class, disableConversion = false)
    private Currency base;

    @Convert(converter = CurrencyConverter.class, disableConversion = false)
    private Currency currency;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate date;

}
