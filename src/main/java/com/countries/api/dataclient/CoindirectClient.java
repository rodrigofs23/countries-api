package com.countries.api.dataclient;

import com.countries.api.entity.country.Country;
import com.countries.api.entity.currency.Currency;
import com.countries.api.exceptions.CoindirectApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CoindirectClient extends RestClient {

  boolean countriesEnabled;
  int maxCountries;
  int maxCurrencies;

  public CoindirectClient(
      @Value("${coindirect-api.countries-enabled}") boolean countriesEnabled,
      @Value("${coindirect-api.max-countries}") int maxCountries,
      @Value("${coindirect-api.max-currencies}") int maxCurrencies,
      @Value("${coindirect-api.url}") String url,
      @Value("${coindirect-api.max-idle-seconds}") int nasaApiMaxIdleSeconds) {
    super("coindirect-api-client-connection-provider", nasaApiMaxIdleSeconds);
    this.webClient = webClient.mutate().baseUrl(url).build();
    this.countriesEnabled = countriesEnabled;
    this.maxCountries = maxCountries;
    this.maxCurrencies = maxCurrencies;
  }

  public List<Country> getCountries() {
    try {
      return webClient
          .get()
          .uri(
              uriBuilder ->
                  uriBuilder
                      .path("/api/country")
                      .queryParam("enabled", countriesEnabled)
                      .queryParam("max", maxCountries)
                      .build())
          .accept(MediaType.APPLICATION_JSON)
          .retrieve()
          .onStatus(
              HttpStatus::isError,
              response -> {
                log.error(
                    "Unable to get countries from Coindirect API. Response: {}",
                    response.toString());
                throw new CoindirectApiException("Unable to get countries from Coindirect API");
              })
          .bodyToMono(new ParameterizedTypeReference<List<Country>>() {})
          .block();

    } catch (Exception e) {
      throw new CoindirectApiException("Unable to reach Coindirect API client", e);
    }
  }

  public List<Currency> getCurrencies() {
    try {
      return webClient
          .get()
          .uri(uriBuilder -> uriBuilder.path("/api/currency/fiat").queryParam("max", 200).build())
          .accept(MediaType.APPLICATION_JSON)
          .retrieve()
          .onStatus(
              HttpStatus::isError,
              response -> {
                log.error(
                    "Unable to get currencies from Coindirect API. Response: {}",
                    response.toString());
                throw new CoindirectApiException("Unable to get currencies from Coindirect API");
              })
          .bodyToMono(new ParameterizedTypeReference<List<Currency>>() {})
          .block();

    } catch (Exception e) {
      throw new CoindirectApiException("Unable to reach Coindirect API client", e);
    }
  }
}
