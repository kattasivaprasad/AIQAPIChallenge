package pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PowerPlants {
    private String id;
    private String name;
    private String state;
    @JsonProperty("annual_net_generation")
    private Double annualNetGeneration;
    private Double latitude;
    private Double longitude;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getAnnualNetGeneration() {
        return annualNetGeneration;
    }

    public void setAnnualNetGeneration(Double annualNetGeneration) {
        this.annualNetGeneration = annualNetGeneration;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
