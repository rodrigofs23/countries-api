# Coindirect API assignment

## How to use
1. Make sure you have Java 17 installed.
2. Run on the terminal: `mvn spring-boot:run`
    1. Alternatively, you can just run the CountriesApi class with the default configuration on your IDE.
3. Easy to test with Swagger OpenAPI exposed in: `http://localhost:9000/swagger-ui/index.html`
    1. Endpoints:
        1. "GET api/v1/countries" display the list of all countries on Coindirect sorted by name and currency.
        2. "GET api/v1/currencies" display the list of all fiat currencies sorted by code.

## Task description
At Coindirect (http://docs.coindirect.com/#api-Countries-listCountries) you will find an API  that lists what countries they support, and some information about them. We want you to fetch information from this API, and process it in such a way that we get:
1. A list of all countries
   * With the following attributes:
     * Name
     * Currency
     * Required documentation
     * Max withdrawal
   * Sorting (Ascending - Descending) on Name and Currency
2. A list of all currencies, with information in which countries each currency is used