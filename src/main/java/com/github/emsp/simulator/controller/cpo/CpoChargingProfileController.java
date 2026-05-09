package com.github.emsp.simulator.controller.cpo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.emsp.simulator.entity.Request;
import com.github.emsp.simulator.model.common.ChargingProfile;
import com.github.emsp.simulator.model.cpo.RemoteCommandResult;
import com.github.emsp.simulator.repository.RequestRepository;

@RestController
public class CpoChargingProfileController {

    @Autowired
    private RequestRepository repository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/api/v1/transactions/{transactionId}/chargingprofile")
    public ResponseEntity<RemoteCommandResult> getChargingProfile(
            @RequestParam(required = false) String uniqueId,
            @RequestParam(required = false) String duration,
            @PathVariable String transactionId) {
        Request request = new Request();
        request.setModule("GET chargingprofile");
        request.setParty("CPO");
        request.setVersion("N/A");
        request.setUid(uniqueId);
        request.setDate(new Date());
        request.setData("uniqueId:" + uniqueId + ", duration:" + duration);

        repository.save(request);

        RemoteCommandResult response = new RemoteCommandResult();
        response.setReply("GET_CHARGING_PROFILE");
        response.setIsOk(true);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/api/v1/transactions/{transactionId}/chargingprofile")
    public ResponseEntity<RemoteCommandResult> deleteChargingProfile(
            @RequestParam(required = false) String uniqueId,
            @PathVariable String transactionId) {
        Request request = new Request();
        request.setModule("DELETE chargingprofile");
        request.setParty("CPO");
        request.setVersion("N/A");
        request.setUid(uniqueId);
        request.setDate(new Date());
        request.setData("uniqueId:" + uniqueId);

        repository.save(request);

        RemoteCommandResult response = new RemoteCommandResult();
        response.setReply("DELETE_CHARGING_PROFILE");
        response.setIsOk(true);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/v1/transactions/{transactionId}/chargingprofile")
    public ResponseEntity<RemoteCommandResult> postChargingProfile(
            @RequestBody ChargingProfile requestBody,
            @RequestParam(required = false) String uniqueId,
            @PathVariable String transactionId) {
        Request request = new Request();
        request.setModule("POST chargingprofile");
        request.setParty("CPO");
        request.setVersion("N/A");
        request.setUid(uniqueId);
        request.setDate(new Date());
        try {
            request.setData("uniqueId:" + uniqueId + ", json:" + objectMapper.writeValueAsString(requestBody));
        } catch (Exception e) {
            e.printStackTrace();
        }

        repository.save(request);

        RemoteCommandResult response = new RemoteCommandResult();
        response.setReply("POST_CHARGING_PROFILE");
        response.setIsOk(true);
        return ResponseEntity.ok().body(response);
    }

}
