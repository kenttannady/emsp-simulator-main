package com.github.emsp.simulator.controller.emsp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.emsp.simulator.entity.Request;
import com.github.emsp.simulator.model.emsp.ChargingProfileResult;
import com.github.emsp.simulator.model.emsp.ResponseNoData;
import com.github.emsp.simulator.model.emsp.Result;
import com.github.emsp.simulator.repository.RequestRepository;

@RestController
public class ChargingProfileController {

    @Autowired
    private RequestRepository repository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/ocpi/emsp/2.2.1/chargingprofiles/{sessionId}")
    public ResponseEntity<ResponseNoData> postChargingProfileV221(
            @RequestBody ChargingProfileResult result,
            @PathVariable String sessionId,
            @RequestHeader(name = "Authorization", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Request request = new Request();
        request.setModule("charingprofile");
        request.setParty("eMSP");
        request.setVersion("ocpi v2.2.1");
        request.setUid(sessionId);
        request.setDate(new Date());
        try {
            request.setData("sessionId: " + sessionId + ", json: " + objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        repository.save(request);

        ResponseNoData response = new ResponseNoData();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/ocpi/emsp/2.1.1/chargingprofiles/{sessionId}")
    public ResponseEntity<ResponseNoData> postCommandV211(
            @RequestBody Result result,
            @PathVariable String sessionId,
            @RequestHeader(name = "Authorization", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        Request request = new Request();
        request.setModule("charingprofile");
        request.setParty("eMSP");
        request.setVersion("ocpi v2.1.1");
        request.setUid(sessionId);
        request.setDate(new Date());
        try {
            request.setData("sessionId: " + sessionId + ", json: " + objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        repository.save(request);

        ResponseNoData response = new ResponseNoData();
        return ResponseEntity.ok().body(response);

    }
}
