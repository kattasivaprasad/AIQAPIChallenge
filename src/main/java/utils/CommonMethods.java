package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;


public class CommonMethods {
    Reporter reporter = new Reporter();
    Response response;
    Map<String, String> headers;

    public Response sendGetRequest(String URL) {
        try {
            headers = createHeaders();
            response = RestAssured.given().headers(headers).get(URL);
            System.out.println("Response: " + response.asString());
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
        } finally {
            System.out.println("URL: " + URL);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response: " + response.asString());
            reporter.addAttachment(URL, response.getStatusCode(), response.asString());
        }
        return response;
    }

    public Response sendGetRequestWithLimit(String URL, String limit) {
        try {

            headers = createHeaders();
            response = RestAssured.given().headers(headers).queryParam("limit", limit).get(URL);
            System.out.println("Response: " + response.asString());
        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
        } finally {
            System.out.println("URL: " + URL);
            System.out.println("Status Code: " + response.getStatusCode());
            System.out.println("Response: " + response.asString());
            reporter.addAttachment(URL, response.getStatusCode(), response.asString());
        }
        return response;
    }

    public Response sendGetRequestWithQueryParams(String URL, String queryParams) {
        try {

            headers = createHeaders();
            response = RestAssured.given().headers(headers).queryParam("state", queryParams).get(URL);
            System.out.println("Response: " + response.asString());

        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
        } finally {
            reporter.addAttachment(URL, response.getStatusCode(), response.asString());
        }
        return response;
    }

    public Map<String, String>  createHeaders() {
        headers = new HashMap<>();
        headers.put("token", "Bearer toto");
        headers.put("accept", "application/json");
        return headers;
    }
}
