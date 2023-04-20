package com.shipmonk.testingday.repository;

import com.shipmonk.testingday.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Currency;
import java.util.List;

public interface ExchangeRatesRepository extends JpaRepository<ExchangeRate, Long> {

    List<ExchangeRate> findExchangeRateByBaseAndDate(Currency currency, LocalDate date);

}
