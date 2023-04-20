package com.shipmonk.testingday.integration;

import com.shipmonk.testingday.model.FixerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Component
public class FixerClient {

    @Value("${fixer.api}")
    private String api;

    @Value("${fixer.token}")
    private String token;

    private final RestTemplate restTemplate;

    @Autowired
    public FixerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FixerDTO getFixerExchangerRates(LocalDate date) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        String dateAsString = date == null ? "latest" : date.toString();

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(api + dateAsString).queryParam("base", "USD").encode().toUriString();

        Map<String, String> params = new HashMap<>();
        params.put("base", "USD");

        ResponseEntity<FixerDTO> fixerDTO = restTemplate.exchange(urlTemplate, HttpMethod.GET, requestEntity, FixerDTO.class, params);
        return fixerDTO.getBody();
    }

}

