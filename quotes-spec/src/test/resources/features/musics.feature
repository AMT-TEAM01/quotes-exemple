Feature: Application Artists

  Scenario: Register a new artist
    Given I have an artist payload
    When  I POST it to the /artists endpoint
    Then  I receive a 201 status code for artists endpoint

  Scenario Outline: Register a new music
    Given I have a <artistNumber> number and a "<musicName>" payload
    When  I POST it to the /musics endpoint
    Then  I receive a <status> status code for musics endpoint
    Examples:
      | artistNumber | musicName | status |
      | 1            | test1   | 201    |
      | 20           | test2   | 404    |
      | 5            | test3   | 201    |

  Scenario Outline: Add a music to a playlist
    Given I have a list of "<musicsNumbers>" number and a <playlistNumber> number
    When  I PUT it to the /playlist/id/musics endpoint
    Then  I receive a <status> status code for playlists endpoint
    Examples:
    | musicsNumbers | playlistNumber  | status  |
    | 1,2,3         | 1               | 201     |
    | 200,2          | 1               | 404     |
    | 1             | 12              | 404     |
