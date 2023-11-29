
Feature: List Resources Functionality

  Background:
    Given I use this path "api/unknown"

  Scenario Outline: Verify page, per_page, total, total_pages
    When I use this query param "page" "1"
    And I use get method
    Then status code should be 200
    And the value of "<key>" should be <value>

    Examples:
      | key         | value |
      | page        | 1     |
      | per_page    | 6     |
      | total       | 12    |
      | total_pages | 2     |


  Scenario Outline: List all data with 2000 and 2001 years
    When I use this query param "page" "<pageNumber>"
    And I use get method
    Then status code should be 200
    And "data" with "year" should be listed
      | 2000 |
      | 2001 |

    Examples:
      | pageNumber |
      | 1          |
      | 2          |



  Scenario Outline:Verify that all color codes starts with "#" and have 7 characters (including "#")
    When I use this query param "page" "<pageNumber>"
    And I use get method
    Then status code should be 200
    And "color" should start with "#" and have 7 characters

    Examples:
      | pageNumber |
      | 1          |
      | 2          |


  Scenario Outline: Verify that all the value of pantone_values in following format "##-####"
    When I use this query param "page" "<pageNumber>"
    And I use get method
    Then status code should be 200
    And all the value of "pantone_value" should be following format as NN-NNNN

    Examples:
      | pageNumber |
      | 1          |
      | 2          |


  Scenario: Verify the year of second element of data in first page is 2001
    When I use this query param "page" "1"
    And I use get method
    Then status code should be 200
    And the "year" of 2. element of data should be 2001


  Scenario Outline: Verify the value of text in support is "To keep ReqRes free, contributions towards server costs are appreciated!"
    When I use this query param "page" "<pageNumber>"
    And I use get method
    Then status code should be 200
    And the value of "support.text" should be "To keep ReqRes free, contributions towards server costs are appreciated!"

    Examples:
      | pageNumber |
      | 1          |
      | 2          |
