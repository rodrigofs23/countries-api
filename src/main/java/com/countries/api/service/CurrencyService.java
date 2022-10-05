package com.countries.api.service;

import com.countries.api.dataclient.CoindirectCache;
import com.countries.api.entity.currency.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class CurrencyService {

  private final CoindirectCache coindirectCache;

  @Autowired
  public CurrencyService(CoindirectCache coindirectCache) {
    this.coindirectCache = coindirectCache;
  }

  public List<Currency> getCurrencies() {
    return sortCurrencies(coindirectCache.getCurrencies());
  }

  private List<Currency> sortCurrencies(List<Currency> currencies) {
    currencies.sort(Comparator.comparing(Currency::getCode));
    return currencies;
  }
}
