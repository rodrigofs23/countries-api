package com.countries.api.service;

import com.countries.api.dataclient.CoindirectCache;
import com.countries.api.entity.country.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CountryService {

  private final CoindirectCache coindirectCache;

  @Autowired
  public CountryService(CoindirectCache coindirectCache) {
    this.coindirectCache = coindirectCache;
  }

  public List<Country> getCountries() {
    List<Country> countries = coindirectCache.getCountries();
    return sortCountries(countries);
  }

  private List<Country> sortCountries(List<Country> countries) {
    countries.sort(
        Comparator.comparing(Country::getName).thenComparing(Country::getDefaultCurrency));
    return countries;
  }
}
