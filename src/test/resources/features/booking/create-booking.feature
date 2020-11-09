Feature: Create Booking

  @All
  Scenario: Creates a new booking in the API

    When User sends request with credentials "credentialsCreate.json" to create new booking
    Then User successfully create new booking