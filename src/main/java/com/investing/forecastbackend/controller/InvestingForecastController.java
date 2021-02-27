package com.investing.forecastbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.investing.forecastbackend.model.ForecastRequest;
import com.investing.forecastbackend.model.ForecastResponse;
import com.investing.forecastbackend.model.InvestmentDetail;
import com.investing.forecastbackend.service.InvestingForecastService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/forecast")
@AllArgsConstructor
public class InvestingForecastController {
    private static final Logger log = LoggerFactory.getLogger(InvestingForecastController.class);
    private InvestingForecastService service;
    private ObjectMapper objectMapper;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<InvestmentDetail>> getInvestmentOptions() throws IOException {
        objectMapper = new ObjectMapper();
        // log.info("Received request to retrieve investment options");
        return ResponseEntity.ok(service.getInvestmentOptions());
    }

    @PostMapping
    @SneakyThrows
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ForecastResponse> getInvestmentOptions(ForecastRequest request) {
        log.info("Received request to forecast investment: {}", objectMapper.writeValueAsString(request));
        return ResponseEntity.ok(service.getInvestmentOptions(request));
    }
}
