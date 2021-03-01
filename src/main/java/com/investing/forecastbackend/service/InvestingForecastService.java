package com.investing.forecastbackend.service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Collections.emptyList;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.investing.forecastbackend.model.ForecastRequest;
import com.investing.forecastbackend.model.ForecastResponse;
import com.investing.forecastbackend.model.InvestmentDetail;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvestingForecastService {

    public List<InvestmentDetail> getInvestmentOptions() throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, List<InvestmentDetail>> details = objectMapper.readValue(
                    Paths.get("src/main/resources/data/investment-details.json").toFile(),
                    new TypeReference<Map<String, List<InvestmentDetail>>>() {
                    });
            return details.get("Investments");

        } catch (Exception e) {
            log.info("Error getting investment options:" + e.toString());
            return emptyList();
        }
    }

    public ForecastResponse getInvestmentOptions(final ForecastRequest request) throws IOException {
        List<InvestmentDetail> details = getInvestmentOptions();
        List<Double> result = getForeCast(request.getRequest(), details);
        ForecastResponse response = new ForecastResponse();
        response.setResponse(result);
        return response;
    }

    public List<Double> getForeCast(Map<String, Double> userRequest, List<InvestmentDetail> details) {
        Map<Integer, Double> totalYearAmount = new HashMap<>();
        for (InvestmentDetail i : details) {
            // user input for category i
            double userInvestmentPercentage = userRequest.get(i.getCategory());
            double userInvestmentDollars = (userInvestmentPercentage / 100) * 10000;
            for (int x = 0; x < 10; x++) {

                // historical interest data for category i in year x
                double historicalInterest = Double.valueOf(i.getData().get(x));
                double currentInterest = (historicalInterest / 100) * userInvestmentDollars;

                userInvestmentDollars = userInvestmentDollars + currentInterest;

                Double currentYearTotal = totalYearAmount.getOrDefault(x, 0.0);
                // add total amount for category i in year x in Map<Integer, Double>
                // totalYearAmount
                // continuously sum total for each investment i in year x
                totalYearAmount.put(x, currentYearTotal + userInvestmentDollars);
            }
        }
        return new ArrayList<>(totalYearAmount.values());
    }

}
