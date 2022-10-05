package com.countries.api.dataclient;

import com.countries.api.entity.country.Country;
import com.countries.api.entity.currency.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.after;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CoindirectCacheTest {

  @Mock private CoindirectClient coindirectClient;
  private CoindirectCache coindirectCache;

  @BeforeEach
  void setUp() {
    coindirectCache = new CoindirectCache(1, 2, coindirectClient);
  }

  @Test
  void getCountries_shouldCallClientOnlyOnce_ifMultipleRequestsMadeBeforeCacheExpires() {
    when(coindirectClient.getCountries()).thenReturn(List.of(new Country()));

    coindirectCache.getCountries();
    coindirectCache.getCountries();

    verify(coindirectClient, times(1)).getCountries();
  }

  @Test
  void getCountries_shouldCallClientTwice_ifCacheWasExpired() {
    when(coindirectClient.getCountries()).thenReturn(List.of(new Country()));

    coindirectCache.getCountries();
    verify(coindirectClient, after(1000).times(1)).getCountries();

    coindirectCache.getCountries();
    verify(coindirectClient, after(2000).times(2)).getCountries();
  }

  @Test
  void getCurrencies_shouldCallClientOnlyOnce_ifMultipleRequestsMadeBeforeCacheExpires() {
    when(coindirectClient.getCurrencies()).thenReturn(List.of(new Currency()));

    coindirectCache.getCurrencies();
    coindirectCache.getCurrencies();

    verify(coindirectClient, times(1)).getCurrencies();
  }

  @Test
  void getCurrencies_shouldCallClientTwice_ifCacheWasExpired() {
    when(coindirectClient.getCurrencies()).thenReturn(List.of(new Currency()));

    coindirectCache.getCurrencies();
    verify(coindirectClient, after(1000).times(1)).getCurrencies();

    coindirectCache.getCurrencies();
    verify(coindirectClient, after(2000).times(2)).getCurrencies();
  }
}
