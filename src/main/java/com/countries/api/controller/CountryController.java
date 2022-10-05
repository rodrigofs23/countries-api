package com.countries.api.controller;

import com.countries.api.entity.country.Country;
import com.countries.api.entity.country.CountryResponse;
import com.countries.api.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/countries")
public class CountryController {

  private final CountryService countryService;

  @Autowired
  public CountryController(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping
  public ResponseEntity<List<CountryResponse>> getCountries() {

    List<Country> countries = countryService.getCountries();
    List<CountryResponse> countryResponse = new ArrayList<>();

    countries.forEach(
        country -> {
          CountryResponse build =
              CountryResponse.builder()
                  .name(country.getName())
                  .code(country.getCode())
                  .currency(country.getDefaultCurrency())
                  .documents(country.getDocuments())
                  .build();
          countryResponse.add(build);
        });

    return ResponseEntity.ok(countryResponse);
  }
}
