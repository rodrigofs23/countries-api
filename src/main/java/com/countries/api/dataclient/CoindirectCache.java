package com.countries.api.dataclient;

import com.countries.api.entity.country.Country;
import com.countries.api.entity.currency.Currency;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class CoindirectCache {

  private final Cache<String, List<Country>> countryCache;
  private final Cache<String, List<Currency>> currencyCache;
  private final CoindirectClient coindirectClient;

  @Autowired
  public CoindirectCache(
      @Value("${coindirect-api.ttl-sec}") long cacheTTL,
      @Value("${coindirect-api.cache-max-size}") int cacheMaxSize,
      CoindirectClient coindirectClient) {
    this.countryCache =
        Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofSeconds(cacheTTL))
            .maximumSize(cacheMaxSize)
            .build();
    this.currencyCache =
        Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofSeconds(cacheTTL))
            .maximumSize(cacheMaxSize)
            .build();
    this.coindirectClient = coindirectClient;
  }

  public List<Country> getCountries() {
    return countryCache.get("countries", key -> coindirectClient.getCountries());
  }

  public List<Currency> getCurrencies() {
    return currencyCache.get("currencies", key -> coindirectClient.getCurrencies());
  }
}
