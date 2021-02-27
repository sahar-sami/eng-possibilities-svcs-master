package com.investing.forecastbackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.investing.forecastbackend.model.ForecastRequest;
import com.investing.forecastbackend.model.ForecastResponse;
import com.investing.forecastbackend.model.InvestmentDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvestingForecastService {

    public List<InvestmentDetail> getInvestmentOptions() throws IOException {
        // TODO read investment options from investment-details.json
        ObjectMapper objectMapper = new ObjectMapper();

        ArrayList read = (ArrayList) objectMapper.readValue(Paths.get(
                "C:/Users/sahar/Downloads/eng-possibilities-svcs-backend/eng-possibilities-svcs-tests/src/main/resources/data/investment-details.json")
                .toFile(), Map.class).get("Investments");

        String str = objectMapper.writeValueAsString(read);
        return objectMapper.readValue(str, new TypeReference<List<InvestmentDetail>>() {
        });
    }

    public ForecastResponse getInvestmentOptions(final ForecastRequest request) {
        // TODO write algorithm to calculate investment forecast from request
        // configuration
        ForecastResponse response = new ForecastResponse();
        response.setResponse(Arrays.asList(11532.0, 12289.49, 14652.39876, 16089.652648700001, 16786.331016873,
                20501.637810853892, 26464.97452638016, 29953.72526338663, 36323.79856502394, 40275.31320184977));
        return response;
    }

}
