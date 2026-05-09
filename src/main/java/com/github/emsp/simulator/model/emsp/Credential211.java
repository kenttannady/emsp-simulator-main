package com.github.emsp.simulator.model.emsp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Credential211 {

    private String url;

    private String token;
    
    @JsonProperty("party_id")
    private String partyId;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("business_details")
    private BusinessDetail businessDetails;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("party_id")
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    @JsonProperty("country_code")
    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @JsonProperty("business_details")
    public BusinessDetail getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(BusinessDetail businessDetails) {
        this.businessDetails = businessDetails;
    }

}
