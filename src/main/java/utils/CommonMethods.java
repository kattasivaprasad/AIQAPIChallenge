package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;


public class CommonMethods {
    Reporter reporter = new Reporter();
    Response response;
    Map<String, String> headers;
    Map<String, String> queryparms;

    public Response sendGetRequestWithQueryParam(String URL, String limit, String queryParam) {
        try {
            headers = createHeaders();
            if (limit.equals("")) {
                response = RestAssured.given().headers(headers).get(URL);
            }else{
                queryparms = getQueryParams(queryParam, limit);
                response = RestAssured.given().headers(headers).queryParams(queryparms).get(URL);
            }
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

    public Map<String, String>  createHeaders() {
        headers = new HashMap<>();
        headers.put("token", "Bearer toto");
        headers.put("accept", "application/json");
        return headers;
    }

    public Map<String, String> getQueryParams(String key, String value) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(key, value);
        return queryParams;
    }

}
