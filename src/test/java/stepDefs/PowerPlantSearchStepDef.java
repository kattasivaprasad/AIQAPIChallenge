package stepDefs;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import powerplants.PowerPlantsResponseValidation;
import utils.CommonMethods;
import java.io.IOException;

public class PowerPlantSearchStepDef {

    PowerPlantsResponseValidation powerPlantsResponse = new PowerPlantsResponseValidation();
    CommonMethods commonMethods = new CommonMethods();
    Response response;
    String URL;
    String responseSizeLimit;
    String baseUrl;
    String pathurl;

    @When("I get a list of power plants using {string},{string} and {string}")
    public void i_get_a_list_of_power_plants_using(String endpoint, String limit, String queryParam) {
        baseUrl = "http://localhost:9000";
        pathurl = getPathUrl(endpoint, limit, queryParam);
        URL = baseUrl + pathurl;
        System.out.println("URL: " + URL);
        response = commonMethods.sendGetRequestWithQueryParam(URL, limit, queryParam);
        Assert.assertEquals("Response is received", true, response.getBody().toString().length() > 0);
    }

    @Then("I validate the response {string}")
    public void i_validate_the_response(String statusCode) throws IOException {
        powerPlantsResponse.setResponse(response);
        powerPlantsResponse.responseValidation(statusCode, responseSizeLimit);
        Assert.assertEquals("Response Validation is failed", true, powerPlantsResponse.isValidationStatus());
    }


    @Then("I validate the response {string} for the {string}")
    public void iValidateTheResponseForThe(String statusCode, String state) throws IOException {
        powerPlantsResponse.setResponse(response);
        powerPlantsResponse.responseValidationForSpecificState(statusCode, state);
        Assert.assertEquals("Response Validation is failed", true, powerPlantsResponse.isValidationStatus());
    }

    private String getPathUrl(String endpoint, String limit, String queryParam) {
        if (queryParam.equalsIgnoreCase("limit")) {
            if (limit != null && !limit.isEmpty()) {
                responseSizeLimit = limit;
                pathurl = endpoint;
            } else {
                responseSizeLimit = "10";
                pathurl = endpoint;
            }
        } else if (queryParam.equalsIgnoreCase("state")) {
            if (limit != null && !limit.isEmpty()) {
                pathurl = endpoint + limit;
            }
        }
        return pathurl;
    }
}
