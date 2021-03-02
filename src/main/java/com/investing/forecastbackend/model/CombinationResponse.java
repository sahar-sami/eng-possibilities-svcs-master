package com.investing.forecastbackend.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Map;
import java.util.List;

@Data
@Getter
@Setter
public class CombinationResponse {
  List<Double> growth;
  Map<String, Double> allocations;
}
