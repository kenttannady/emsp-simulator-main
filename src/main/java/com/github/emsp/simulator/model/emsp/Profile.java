package com.github.emsp.simulator.model.emsp;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.emsp.simulator.model.common.ChargingProfile;

public class Profile {

    @JsonProperty("start_date_time")
    private Date startDateTime;
    
    @JsonProperty("charging_profile")
    private ChargingProfile chargingProfile;

}
