Feature: Health check

  @All
  Scenario: Check endpoint to confirm whether the API is up and running

    When Request get health check was sent by user
    Then Client receive API success response
