package com.shipmonk.testingday.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Table(name = "EXCHANGES_RATES")
@IdClass(ExchangeRateId.class)
public class ExchangeRate {

    @Id
    private Currency base;
    @Id
    private Currency currency;
    @Id
    private LocalDate date;

    @Column(nullable = false)
    private BigDecimal rate;

    public Currency getBase() {
        return base;
    }

    public void setBase(Currency base) {
        this.base = base;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

}
