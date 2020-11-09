Feature: Get Booking

  Background:
    * Request to get booking by ids was sent
    * Client successfully receive booking by ids

  @All
  Scenario: Returns a specific booking in the API
    When User sends request to get booking
    Then User successfully receive booking