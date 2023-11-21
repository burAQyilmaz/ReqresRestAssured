Feature: List User Functionality

  Background:
    Given I use this path "api/users"

  Scenario: List all users from first/second page
    When I use this query param "page" "1"
    And I use get method
    Then status code should be 200
    And all users should be listed

  Scenario: Verify that Content-Type is "application/json; charset=utf-8" for first/second page
    When I use this query param "page" "1"
    And I use get method
    Then status code should be 200
    And response headers "Content-Type" should have this value "application/json; charset=utf-8"

  Scenario: Verify that host is "reqres.in" for first/second page
    Then request headers "Host" should have this value "reqres.in"

  Scenario: Verify that connection is "keep-alive" for first/second page
    When I use this query param "page" "1"
    And I use get method
    Then status code should be 200
    And response headers "Connection" should have this value "keep-alive"

  Scenario: Check if the response time is less then 1000 for first/second page
    When I use this query param "page" "1"
    And I use get method
    Then status code should be 200
    And response time is less then 1000

  Scenario Outline: Verify page, per_page, total, total_pages for first/second page
    When I use this query param "page" "2"
    And I use get method
    Then status code should be 200
    And the value of "<key>" should be <value>

    Examples:
      | key         | value |
      | page        | 2     |
      | per_page    | 6     |
      | total       | 12    |
      | total_pages | 2     |

  Scenario Outline: List all users
    When I use this query param "page" "<pageNum>"
    And I use get method
    Then status code should be 200
    And all users should be listed

    Examples:
      | pageNum |
      | 1       |
      | 2       |


  Scenario: Check if support url is working for first/second page
    When I use this query param "page" "1"
    And I use get method
    Then status code should be 200
    And "support.url" url should be working


  Scenario: List all user first names from first/second page
    When I use this query param "page" "1"
    And I use get method
    Then status code should be 200
    And "data.first_name" should be listed


  Scenario Outline: List all users names whose ids are odd
    When I use this query param "page" "<pageNum>"
    And I use get method
    Then status code should be 200
    And odd ids' names should be listed

    Examples:
      | pageNum |
      | 1       |
      | 2       |


  Scenario Outline:Check if each email address contains first name
    When I use this query param "page" "<pageNum>"
    And I use get method
    Then status code should be 200
    And each email address should contain first name

    Examples:
      | pageNum |
      | 1       |
      | 2       |


  @wip
  Scenario Outline:Verify that if given ids and first_names match
    When I use this query param "page" "<pageNum>"
    And I use get method
    Then status code should be 200
    And user "<id>" and "<first_name>" should match

    Examples:
      | pageNum | id | first_name |
      | 1       | 1  | George     |
      | 1       | 2  | Janet      |
      | 1       | 3  | Emma       |
      | 1       | 4  | Eve        |
      | 1       | 5  | Charles    |
      | 1       | 6  | Tracey     |
      | 2       | 7  | Michael    |
      | 2       | 8  | Lindsay    |
      | 2       | 9  | Tobias     |
      | 2       | 10 | Byron      |
      | 2       | 11 | George     |
      | 2       | 12 | Rachel     |