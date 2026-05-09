package com.github.emsp.simulator.model.common;

import java.util.List;

public class HandshakeResponse {
    private String status;
    private String message;

    private List<SimulationStep> steps;

    public static HandshakeResponse ok() {
        return new HandshakeResponse("OK", "");
    }

    public static HandshakeResponse ok(String message) {
        return new HandshakeResponse("OK", message);
    }

    public static HandshakeResponse error(String message) {
        return new HandshakeResponse("Error", message);
    }

    public HandshakeResponse() {}

    public HandshakeResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SimulationStep> getSteps() {
        return steps;
    }

    public void setSteps(List<SimulationStep> steps) {
        this.steps = steps;
    }

}
