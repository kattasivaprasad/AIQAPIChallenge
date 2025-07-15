package stepDefs;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import promotions.PowerPlantsResponseValidation;
import utils.CommonMethods;

import java.io.IOException;

public class PromotionsStepDef {

    PowerPlantsResponseValidation powerPlantsResponse = new PowerPlantsResponseValidation();
//    PromotionsResponseValidation responseValidation = new PromotionsResponseValidation();
    CommonMethods commonMethods = new CommonMethods();
    Response response;
    String  URL;
    String responseSizeLimit;


    @When("I get a list of power plants using {string},{string}")
    public void i_get_a_list_of_power_plants_using(String endpoint, String limit) {
        if (limit == null || limit.equals("")) {
            limit = "10";
            responseSizeLimit=limit;
        }else{
            responseSizeLimit = limit;
        }
        String baseUrl = "http://localhost:9000";
            URL = baseUrl+endpoint;
        System.out.println("URL: " + URL);
        if (!limit.equals("")) {
            response = commonMethods.sendGetRequestWithLimit(URL,limit);
        }else{
            response = commonMethods.sendGetRequest(URL);
        }
        Assert.assertEquals("Response is received", true, response.getBody().toString().length() > 0);
    }


    @Then("I validate the response {string}")
    public void i_validate_the_response(String statusCode) throws IOException {
        powerPlantsResponse.setResponse(response);
        powerPlantsResponse.responseValidation(statusCode,responseSizeLimit);
//        Assert.assertEquals("Response Validation is failed", true, responseValidation.isValidationStatus());
    }

    @When("I get a list of power plants for the state {string},{string}")
    public void iGetAListOfPowerPlantsForTheState(String state, String endpoint) {
        String baseUrl = "http://localhost:9000";
        URL = baseUrl+endpoint+state;
        System.out.println("URL: " + URL);
        response = commonMethods.sendGetRequestWithQueryParams(URL, state);
        Assert.assertEquals("Response is received", true, response.getBody().toString().length() > 0);
    }

    @Then("I validate the response {string} for the {string}")
    public void iValidateTheResponseForThe(String statusCode, String state) throws IOException {
        powerPlantsResponse.setResponse(response);
        powerPlantsResponse.responseValidation(statusCode);
//        Assert.assertEquals("Response Validation is failed", true, responseValidation.isValidationStatus());

    }


}
