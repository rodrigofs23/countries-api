package com.countries.api.entity.country;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryResponse {
  private String name;
  private String code;
  private String currency;
  List<CountryDocument> documents;
}
