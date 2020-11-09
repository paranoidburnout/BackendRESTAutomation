Feature: Delete booking

  Background:
    * User sends request with credentials "credentials.json" to generate token
    * User successfully receive new token
    * Request to get booking by ids was sent
    * Client successfully receive booking by ids

  @All
  Scenario: Delete booking
    When User sends request to delete booking
    Then Booking was actually deleted