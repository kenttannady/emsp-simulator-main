package com.github.emsp.simulator.controller.cpo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.emsp.simulator.entity.Request;
import com.github.emsp.simulator.model.cpo.LocationRemoteStartDto;
import com.github.emsp.simulator.model.cpo.RemoteCommandResult;
import com.github.emsp.simulator.repository.RequestRepository;

@RestController
public class CpoCommandController {
    
    @Autowired
    private RequestRepository repository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/api/v1/locations/{locationId}/remotestartinternal")
    public ResponseEntity<RemoteCommandResult> postStartSession(
            @RequestBody LocationRemoteStartDto requestBody,
            @RequestParam(required = false) String chargePointOwnerId,
            @PathVariable String locationId) {

        Request request = new Request();
        request.setModule("commands remotestartinternal");
        request.setParty("CPO");
        request.setVersion("N/A");
        request.setUid(requestBody.getUid());
        request.setDate(new Date());
        try {
            request.setData("chargePointOwnerId:" + chargePointOwnerId + ", uniqueId:" + ", json:" + objectMapper.writeValueAsString(requestBody));
        } catch (Exception e) {
            e.printStackTrace();
        }

        repository.save(request);

        RemoteCommandResult response = new RemoteCommandResult();
        response.setReply("START_SESSION");
        response.setIsOk(true);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/v1/locations/{locationId}/remotestopinternal/{sessionId}")
    public ResponseEntity<RemoteCommandResult> postStopSession(
            @PathVariable String locationId,
            @PathVariable String sessionId,
            @RequestParam(required = false) String uniqueId, 
            @RequestParam(required = false) String chargePointOwnerId) {

        Request request = new Request();
        request.setModule("commands remotestopinternal");
        request.setParty("eMSP");
        request.setVersion("N/A");
        request.setUid(uniqueId);
        request.setDate(new Date());
        request.setData("sessionId:" + sessionId + ", locationId:" + locationId+ ", chargePointOwnerId:" + chargePointOwnerId + ", uniqueId:" + uniqueId);;
        repository.save(request);

        RemoteCommandResult response = new RemoteCommandResult();
        response.setReply("STOP_SESSION");
        response.setIsOk(true);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/api/v1/locations/{locationId}/unlockconnectorinternal/{connectorId}")
    public ResponseEntity<RemoteCommandResult> postUnlockConnector(
            @PathVariable String locationId,
            @PathVariable String connectorId,
            @RequestParam(required = false) String uniqueId, 
            @RequestParam(required = false) String chargePointOwnerId
            ) {

        Request request = new Request();
        request.setModule("commands unlockconnectorinternal");
        request.setParty("eMSP");
        request.setVersion("N/A");
        request.setUid(uniqueId);
        request.setDate(new Date());
        request.setData("connectorId:" + connectorId + ", locationId:" + locationId + ", chargePointOwnerId:" + chargePointOwnerId + ", uniqueId:" + uniqueId);
        repository.save(request);
        
        RemoteCommandResult response = new RemoteCommandResult();
        response.setReply("UNLOCK_CONNECTOR");
        response.setIsOk(true);
        return ResponseEntity.ok().body(response);
    }

}
