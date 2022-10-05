package com.countries.api.service;

import com.countries.api.dataclient.CoindirectCache;
import com.countries.api.entity.country.Country;
import com.countries.api.entity.country.CountryDocument;
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
class CountryServiceTest {

  @Mock private CoindirectCache coindirectCache;
  @InjectMocks private CountryService countryService;

  @Test
  void getCountries_shouldReturnCountriesListSortedByNameAndCurrency() {
    when(coindirectCache.getCountries()).thenReturn(setCountriesList());

    List<Country> countries = countryService.getCountries();

    assertEquals("Norway", countries.get(0).getName());
    assertEquals("NOK", countries.get(0).getDefaultCurrency());
    assertEquals("United States", countries.get(1).getName());
    assertEquals("USD", countries.get(1).getDefaultCurrency());
  }

  @Test
  void getCountries_shouldThrowException() {
    when(coindirectCache.getCountries())
        .thenThrow(new CoindirectApiException("Error getting countries"));

    RuntimeException exception =
        assertThrows(CoindirectApiException.class, () -> countryService.getCountries());

    assertEquals("Error getting countries", exception.getMessage());
  }

  private List<Country> setCountriesList() {
    return Arrays.asList(
        Country.builder()
            .id(1L)
            .name("United States")
            .code("USA")
            .defaultCurrency("USD")
            .documents(
                List.of(
                    CountryDocument.builder()
                        .id(1L)
                        .code("code 1")
                        .required(true)
                        .description("description 1")
                        .build(),
                    CountryDocument.builder()
                        .id(2L)
                        .code("code 2")
                        .required(true)
                        .description("description 2")
                        .build()))
            .build(),
        Country.builder()
            .id(2L)
            .name("Norway")
            .code("NO")
            .defaultCurrency("NOK")
            .documents(
                List.of(
                    CountryDocument.builder()
                        .id(3L)
                        .code("code 3")
                        .required(true)
                        .description("description 3")
                        .build(),
                    CountryDocument.builder()
                        .id(4L)
                        .code("code 4")
                        .required(true)
                        .description("description 4")
                        .build()))
            .build());
  }
}
