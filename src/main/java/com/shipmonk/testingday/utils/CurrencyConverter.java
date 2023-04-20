package com.shipmonk.testingday.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Currency;

@Converter
public class CurrencyConverter implements AttributeConverter<Currency, String> {

    @Override
    public String convertToDatabaseColumn(Currency attribute) {
        return attribute.getCurrencyCode();
    }

    @Override
    public Currency convertToEntityAttribute(String dbData) {
        return Currency.getInstance(dbData);
    }

}
