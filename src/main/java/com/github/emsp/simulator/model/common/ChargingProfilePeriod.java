package com.github.emsp.simulator.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChargingProfilePeriod {

    @JsonProperty("start_period")
    private Integer startPeriod;

    private Integer limit;

    public Integer getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Integer startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
