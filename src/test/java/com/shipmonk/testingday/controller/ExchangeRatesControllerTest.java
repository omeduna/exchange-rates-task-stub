package com.shipmonk.testingday.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.shipmonk.testingday.integration.FixerClient;
import com.shipmonk.testingday.model.ExchangeRatesDTO;
import com.shipmonk.testingday.model.FixerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeRatesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FixerClient fixerClient;

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void getRatesTest() throws Exception {
        InputStream inJson = FixerDTO.class.getResourceAsStream("/test_data.json");
        FixerDTO fixerDTO = objectMapper.readValue(inJson, FixerDTO.class);
        when(fixerClient.getFixerExchangerRates(any())).thenReturn(fixerDTO);

        ExchangeRatesDTO expectedDTO = new ExchangeRatesDTO();
        expectedDTO.setSuccess(true);
        expectedDTO.setTimestamp(Instant.ofEpochSecond(1681991943L));
        expectedDTO.setBase(Currency.getInstance("USD"));
        expectedDTO.setDate(LocalDate.of(2023, 4, 20));
        expectedDTO.setRates(Map.of(
            Currency.getInstance("AED"), new BigDecimal("3.6725"),
            Currency.getInstance("AFN"), new BigDecimal("86.089909"),
            Currency.getInstance("ALL"), new BigDecimal("102.151861")
        ));
        String expectedJson = asJsonString(expectedDTO);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/rates/2023-04-20"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson));
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
