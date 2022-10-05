package com.countries.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CountriesApi {

  public static void main(String[] args) {
    SpringApplication.run(CountriesApi.class, args);
  }
}
