Feature: Get booking by ids

  @All
  Scenario: Returns the ids of the booking in the API
    When Request to get booking by ids was sent
    Then Client successfully receive booking by ids

