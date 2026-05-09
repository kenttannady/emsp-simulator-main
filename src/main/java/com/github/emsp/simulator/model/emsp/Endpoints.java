package com.github.emsp.simulator.model.emsp;

import java.util.List;

public class Endpoints {
    private String version;
    private List<Endpoint> endpoints;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    public void setEndpoints(List<Endpoint> endpoints) {
        this.endpoints = endpoints;
    }

}
