package com.shipmonk.testingday.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@Data
public class FixerDTO {

    private boolean success;
    private boolean historical;
    private Instant timestamp;
    private Currency base;
    private LocalDate date;
    private Map<String, BigDecimal> rates = new HashMap<>();

}
