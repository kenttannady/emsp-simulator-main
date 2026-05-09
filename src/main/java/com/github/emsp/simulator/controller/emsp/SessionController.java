package com.github.emsp.simulator.controller.emsp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.emsp.simulator.entity.Request;
import com.github.emsp.simulator.model.emsp.ResponseNoData;
import com.github.emsp.simulator.repository.RequestRepository;
import com.github.emsp.simulator.service.JitterSimulatorService;

@RestController
public class SessionController {

    // PUT and Patch
    @Autowired
    private RequestRepository repository;

    @Autowired
    private JitterSimulatorService service;

    // https://emspsimulator.com/ocpi/emsp/2.1.1/sessions/ID/HWK/443E6420-0239-49D6-A714-9BF62AFAC16B'
    @PutMapping("/ocpi/emsp/2.1.1/sessions/{countryCode}/{partyId}/{uid}")
    public ResponseEntity<ResponseNoData> postSessionV211(
            @RequestBody String json,
            @PathVariable String countryCode,
            @PathVariable String partyId,
            @PathVariable String uid,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "retry", required = false) Integer retry,
            @RequestParam(name = "timeout", required = false) Integer timeout,
            @RequestHeader(name = "Authorization", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
  
        Request request = new Request();
        request.setUid(uid);
        request.setParty("eMSP");
        request.setVersion("ocpi v2.1.1");
        request.setModule("sessions module");
        request.setDate(new Date());
        request.setData("countryCode:" + countryCode + ", partyId:" + partyId + ", json:" + json);
        
        status = service.simulateJitter(uid, retry, status, timeout, request);
        if(status != 200){
            return ResponseEntity.status(status).body(null);
        }

        ResponseNoData responseNoData = new ResponseNoData();
        return ResponseEntity.ok().body(responseNoData);
    }

    @PutMapping("/ocpi/emsp/2.2.1/sessions/{countryCode}/{partyId}/{uid}")
    public ResponseEntity<ResponseNoData> postSessionV221(
            @RequestBody String json,
            @PathVariable String countryCode,
            @PathVariable String partyId,
            @PathVariable String uid,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "retry", required = false) Integer retry,
            @RequestParam(name = "timeout", required = false) Integer timeout,
            @RequestHeader(name = "Authorization", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
   
        Request request = new Request();
        request.setUid(uid);
        request.setParty("eMSP");
        request.setVersion("ocpi v2.2.1");
        request.setModule("sessions module");
        request.setDate(new Date());
        request.setData("countryCode:" + countryCode + ", partyId:" + partyId + ", json:" + json);
                
        status = service.simulateJitter(uid, retry, status, timeout, request);
        if(status != 200){
            return ResponseEntity.status(status).body(null);
        }

        repository.save(request);
        ResponseNoData responseNoData = new ResponseNoData();
        return ResponseEntity.ok().body(responseNoData);
    }
}
