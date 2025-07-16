@Test
Feature: As an API user i will verify the HTTPS Status Codes

  @GetAPIWithLimit
  Scenario Outline: Verify the power-plants/search API response with default limit and with specific limit
    When I get a list of power plants using "<url>","<limit>" and "<queryParam>"
    Then I validate the response "<statusCode>"
    Examples:
      | url                          | statusCode | limit | queryParam |
      | /api/power-plants/search     | 200        |       | limit      |
      | /api/power-plants/search/top | 200        | 100   | limit      |


  @GetAPIForState
  Scenario Outline: Verify the power-plants/search/state api with specific state parameter
    When I get a list of power plants using "<url>","<state>" and "<queryParam>"
    Then I validate the response "<statusCode>" for the "<state>"

    Examples:
      | url                             | statusCode | state | queryParam |
      | /api/power-plants/search/state/ | 200        | GA    | state      |
      | /api/power-plants/search/state/ | 200        | KS    | state      |