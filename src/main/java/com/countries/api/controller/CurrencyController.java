package com.countries.api.controller;

import com.countries.api.entity.currency.Currency;
import com.countries.api.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/currencies")
public class CurrencyController {

  private final CurrencyService currencyService;

  @Autowired
  public CurrencyController(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  @GetMapping
  public ResponseEntity<List<Currency>> getCurrencies() {
    return ResponseEntity.ok(currencyService.getCurrencies());
  }
}
