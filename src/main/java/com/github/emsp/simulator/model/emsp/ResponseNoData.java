package com.github.emsp.simulator.model.emsp;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseNoData {
    private Integer statusCode = 1000; // success
    private String statusMessage = "Success";
    private Date timestamp = new Date();

    @JsonProperty("status_code")
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @JsonProperty("status_message")
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
