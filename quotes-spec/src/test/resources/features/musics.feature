Feature: Application Artists

  Scenario: Register a new artist
    Given I have an artist payload
    When  I POST it to the /artists endpoint
    Then  I receive a 201 status code