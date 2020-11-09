Feature: Partial update Booking

  Background:
    * User sends request with credentials "credentials.json" to generate token
    * User successfully receive new token
    * Request to get booking by ids was sent
    * Client successfully receive booking by ids

  @All
  Scenario: Updates a current booking with a partial payload

    When Request with credentials "credentialsPartUpdate.json" was sent by user
    Then User successfully update booking