package com.github.emsp.simulator.model.emsp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Role {
    private String role;

    @JsonProperty("party_id")
    private String partyId;

    @JsonProperty("country_code")
    private String countryCode;

    @JsonProperty("business_details")
    private BusinessDetail businessDetails;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
