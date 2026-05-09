package com.github.emsp.simulator.model.cpo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RemoteCommandResult {
    public String reply;

    public boolean isOk = false;

    @JsonProperty("Reply")
    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @JsonProperty("IsOk")
    public boolean getIsOk() {
        return isOk;
    }

    public void setIsOk(boolean isOk) {
        this.isOk = isOk;
    }

}
