package com.investing.forecastbackend.model;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
// TODO Model the data read from ../resources/data/investment-details.json
public class InvestmentDetail {
  private String category;
  private String minimum;
  private List<String> data;
}
