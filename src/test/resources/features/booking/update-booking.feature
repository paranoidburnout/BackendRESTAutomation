Feature: Update Booking

  Background:
    * User sends request with credentials "credentials.json" to generate token
    * User successfully receive new token
    * Request to get booking by ids was sent
    * Client successfully receive booking by ids

  @All
  Scenario: Updates a current booking

    When Client send Request with credentials "credentialsUpdate.json"
    Then Booking was updated