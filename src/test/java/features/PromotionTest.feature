@Test
Feature: As an API user i will verify the HTTPS Status Codes

  @PositiveTestCase @GetAPIWithLimit
  Scenario Outline: Verify the power-plants/search API response with default limit and with specific limit
    When I get a list of power plants using "<url>","<limit>"
    Then I validate the response "<statusCode>"
    Examples:
      | url                          | statusCode | limit |
      | /api/power-plants/search     | 200        |       |
      | /api/power-plants/search/top | 200        | 100   |


    @GetAPIForState
  Scenario Outline: Verify the power-plants/search/state api with specific state parameter
    When I get a list of power plants for the state "<state>","<url>"
    Then I validate the response "<statusCode>" for the "<state>"

    Examples:
      | url                             | statusCode | state |
      | /api/power-plants/search/state/ | 200        | GA    |
      | /api/power-plants/search/state/ | 200        | KS    |