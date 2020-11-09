Feature: Client authentication

  @All
  Scenario: Creating a token

    When User sends request with credentials "credentials.json" to generate token
    Then User successfully receive new token
