package com.github.emsp.simulator.model.common;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChargingProfile {

    @JsonProperty("start_date_time")
    private Date startDateTime;

    private Integer duration;

    @JsonProperty("charging_rate_unit")
    private String chargingRateUnit;
    
    @JsonProperty("min_charging_rate")
    private Integer minChargingRate;

    @JsonProperty("charging_profile_period")
    private List<ChargingProfilePeriod> chargingProfilePeriod;

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getChargingRateUnit() {
        return chargingRateUnit;
    }

    public void setChargingRateUnit(String chargingRateUnit) {
        this.chargingRateUnit = chargingRateUnit;
    }

    public Integer getMinChargingRate() {
        return minChargingRate;
    }

    public void setMinChargingRate(Integer minChargingRate) {
        this.minChargingRate = minChargingRate;
    }

    public List<ChargingProfilePeriod> getChargingProfilePeriod() {
        return chargingProfilePeriod;
    }

    public void setChargingProfilePeriod(List<ChargingProfilePeriod> chargingProfilePeriod) {
        this.chargingProfilePeriod = chargingProfilePeriod;
    }

}
