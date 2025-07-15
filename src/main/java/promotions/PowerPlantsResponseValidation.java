package promotions;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import pojos.PowerPlants;
import pojos.PowerPlantsSearchResponse;
import utils.Reporter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class PowerPlantsResponseValidation {

    Reporter reporter = new Reporter();
    private Response response;
    private int statusCode;
    private String errorCode;
    private String errorMessage;
    private boolean validationStatus = true;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setValidationStatus(boolean validationStatus) {
        this.validationStatus = validationStatus;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void responseValidation(String statusCode, String responseSizeLimit) throws IOException {
        assertEqualCheck("Response status code validation failed ",
                statusCode, String.valueOf(response.getStatusCode()), "Status Code");

        List<PowerPlants> powerPlantsList = response.as(new TypeRef<List<PowerPlants>>() {});
        int sizeOfResponse = powerPlantsList.size();

        assertEqualCheck("Limit Filter Has not worked properly",
                responseSizeLimit, String.valueOf(sizeOfResponse),
                "Limit Filter");
        assertTrueCheck("PowerPlants list should not be null or empty", powerPlantsList != null && !powerPlantsList.isEmpty());
        powerPlantsList.stream().filter(Objects::nonNull).forEach(powerPlants -> {
            try {
                powerPlantValidation(powerPlants);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void responseValidation(String statusCode) throws IOException {
        assertEqualCheck("Response status code validation failed ",
                statusCode, String.valueOf(response.getStatusCode()), "Status Code");

        List<PowerPlants> powerPlantsList = response.as(new TypeRef<List<PowerPlants>>() {});
        assertTrueCheck("PowerPlants list should not be null or empty", powerPlantsList != null && !powerPlantsList.isEmpty());
        powerPlantsList.stream().filter(Objects::nonNull).forEach(powerPlants -> {
            try {
                powerPlantValidation(powerPlants);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public void responseValidationForSpecificId(String statusCode) throws IOException {
      assertEqualCheck("Response Code is not matching",
              statusCode,String.valueOf(response.getStatusCode()), "Status Code");
      PowerPlantsSearchResponse powerPlantsSearchResponse = response.as(PowerPlantsSearchResponse.class);
      powerPlantsSearchResponse.getPowerPlants().stream().filter(Objects::nonNull).forEach(powerPlants -> {
          try {
              powerPlantValidation(powerPlants);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
      });
    }

    public void assertTrueCheck(String field, boolean val) throws IOException {
        try {
            Assert.assertEquals(field + " field is not present in response", true, val);
            reporter.addAllureAttachment("Parameter " + field + " is Present in response", String.valueOf(val));
        } catch (AssertionError | IOException e) {
            setValidationStatus(false);
            reporter.addAllureAttachment("Parameter " + field + " is Not Present in response", val);
        }
    }

    public void assertEqualCheck(String errorMessage, String expected, String actual, String field) throws IOException {
        try {
            Assert.assertEquals(errorMessage, expected, actual);
            reporter.addAllureAttachment("Parameter " + field.toUpperCase() + " is Present in response as expected", actual);
        } catch (AssertionError | IOException e) {
            setValidationStatus(false);
            reporter.addAllureAttachment("Parameter " + field + " is missing in response", field);
        }
    }

    public void powerPlantValidation(PowerPlants powerPlants) throws IOException {
        reporter.addAllureAttachment("ID Is: ",powerPlants.getId());
        assertTrueCheck("Name should not be null", powerPlants.getName() != null);
        assertTrueCheck("State should not be null", powerPlants.getName() instanceof String);
        if (powerPlants.getAnnualNetGeneration() != null) {
            assertTrueCheck("Annual Net Generation should be a number",
                    powerPlants.getAnnualNetGeneration() instanceof Number);
        }
        assertTrueCheck("Latitude should not be null", powerPlants.getLatitude() != null);
        assertTrueCheck("Latitude should not be null", powerPlants.getLongitude() != null);
    }
}
