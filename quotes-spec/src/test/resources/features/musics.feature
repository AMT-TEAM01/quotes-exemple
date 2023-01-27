Feature: Application Artists

  Scenario: Register a new artist
    Given I have an artist payload
    When  I POST it to the /artists endpoint
    Then  I receive a 201 status code

  Scenario Outline: Register a new music
    Given I have a <artistNumber> number and a "<musicName>" payload
    When  I POST it to the /musics endpoint
    Then  I receive a <status> status code

    Examples:
      | artistNumber | musicName | status |
      | 1            | test1   | 201    |
      | 20           | test2   | 404    |
      | 5            | test3   | 201    |