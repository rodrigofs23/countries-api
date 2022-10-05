package com.countries.api.service;

import com.countries.api.dataclient.CoindirectCache;
import com.countries.api.entity.currency.Currency;
import com.countries.api.exceptions.CoindirectApiException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

  @Mock private CoindirectCache coindirectCache;
  @InjectMocks private CurrencyService currencyService;

  @Test
  void getCurrencies_shouldReturnCurrencies() {
    when(coindirectCache.getCurrencies()).thenReturn(setCurrenciesList());

    List<Currency> currencies = currencyService.getCurrencies();

    assertEquals("NOK", currencies.get(0).getCode());
    assertEquals("USD", currencies.get(1).getCode());
  }

  @Test
  void getCountries_shouldThrowException() {
    when(coindirectCache.getCurrencies())
        .thenThrow(new CoindirectApiException("Error getting currencies"));

    RuntimeException exception =
        assertThrows(CoindirectApiException.class, () -> currencyService.getCurrencies());

    assertEquals("Error getting currencies", exception.getMessage());
  }

  private List<Currency> setCurrenciesList() {
    return Arrays.asList(
        Currency.builder().code("USD").name("United States Dollar").build(),
        Currency.builder().code("NOK").name("Norwegian Krone").build());
  }
}
