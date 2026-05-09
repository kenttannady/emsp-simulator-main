package com.github.emsp.simulator.model.cpo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationRemoteStartDto {
    public int connectorId;

    public String partyId;

    public String authorizationReference;

    public String authId;

    public String uid;

    public String issuer;

    public String visualNumber;

    public String emsproviderId;

    public String requestUniqueId;

    @JsonProperty("ConnectorId")
    public int getConnectorId() {
        return connectorId;
    }

    public void setConnectorId(int connectorId) {
        this.connectorId = connectorId;
    }

    @JsonProperty("PartyId")
    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    @JsonProperty("AuthorizationReference")
    public String getAuthorizationReference() {
        return authorizationReference;
    }

    public void setAuthorizationReference(String authorizationReference) {
        this.authorizationReference = authorizationReference;
    }

    @JsonProperty("AuthId")
    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    @JsonProperty("Uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty("Issuer")
    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @JsonProperty("VisualNumber")
    public String getVisualNumber() {
        return visualNumber;
    }

    public void setVisualNumber(String visualNumber) {
        this.visualNumber = visualNumber;
    }

    @JsonProperty("EmsproviderId")
    public String getEmsproviderId() {
        return emsproviderId;
    }

    public void setEmsproviderId(String emsproviderId) {
        this.emsproviderId = emsproviderId;
    }

    @JsonProperty("RequestUniqueId")
    public String getRequestUniqueId() {
        return requestUniqueId;
    }

    public void setRequestUniqueId(String requestUniqueId) {
        this.requestUniqueId = requestUniqueId;
    }

}