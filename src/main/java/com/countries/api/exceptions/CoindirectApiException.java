package com.countries.api.exceptions;

public class CoindirectApiException extends RuntimeException {

  public CoindirectApiException(String message) {
    super(message);
  }

  public CoindirectApiException(String message, Exception cause) {
    super(message, cause);
  }
}
