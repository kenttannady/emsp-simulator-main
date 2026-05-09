package com.github.emsp.simulator.controller.emsp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.emsp.simulator.model.emsp.Endpoint;
import com.github.emsp.simulator.model.emsp.Endpoints;
import com.github.emsp.simulator.model.emsp.Response;
import com.github.emsp.simulator.service.JitterSimulatorService;

@RestController
public class EndpointController {

    @Autowired
    private JitterSimulatorService service;

    @GetMapping("/ocpi/emsp/2.1.1/")
    public ResponseEntity<Response<Endpoints>> getEndpoint211(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "retry", required = false) Integer retry,
            @RequestParam(name = "timeout", required = false) Integer timeout,
            @RequestParam(name = "uid", required = false) String uid) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        status = service.simulateJitter("endpoint module", "ocpi v2.1.1", uid, retry, status, timeout);
        if(status != 200){
            return ResponseEntity.status(status).body(null);
        }
        // validate Token A
        Endpoints endpoints = new Endpoints();
        endpoints.setVersion("2.1.1");
        List<Endpoint> endpointList = new ArrayList<>();
        Endpoint endpoint = new Endpoint();
        endpoint.setIdentifier("credentials");
        endpoint.setUrl("https://emspsimulator.com/ocpi/emsp/2.1.1/credentials");
        endpointList.add(endpoint);
        endpoint = new Endpoint();
        endpoint.setIdentifier("locations");
        endpoint.setUrl("https://emspsimulator.com/ocpi/emsp/2.1.1/locations");
        endpointList.add(endpoint);
        endpoint = new Endpoint();
        endpoint.setIdentifier("commands");
        endpoint.setUrl("https://emspsimulator.com/ocpi/emsp/2.1.1/commands");
        endpointList.add(endpoint);
        endpoints.setEndpoints(endpointList);
        Response<Endpoints> endpointsResponse = new Response<>();
        endpointsResponse.setData(endpoints);
        return ResponseEntity.ok().body(endpointsResponse);

    }

    @GetMapping("/ocpi/emsp/2.2.1/")
    public ResponseEntity<Response<Endpoints>> getEndpoint221(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "retry", required = false) Integer retry,
            @RequestParam(name = "timeout", required = false) Integer timeout,
            @RequestParam(name = "uid", required = false) String uid) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        status = service.simulateJitter("endpoint module", "ocpi v2.2.1", uid, retry, status, timeout);
        if(status != 200){
            return ResponseEntity.status(status).body(null);
        }
        // validate Token A
        Endpoints endpoints = new Endpoints();
        endpoints.setVersion("2.2.1");
        List<Endpoint> endpointList = new ArrayList<>();
        Endpoint endpoint = new Endpoint();
        endpoint.setIdentifier("credentials");
        endpoint.setUrl("https://emspsimulator.com/ocpi/emsp/2.2.1/credentials");
        endpointList.add(endpoint);
        endpoint = new Endpoint();
        endpoint.setIdentifier("locations");
        endpoint.setUrl("https://emspsimulator.com/ocpi/emsp/2.2.1/locations");
        endpointList.add(endpoint);
        endpoints.setEndpoints(endpointList);
        endpoint = new Endpoint();
        endpoint.setIdentifier("commands");
        endpoint.setUrl("https://emspsimulator.com/ocpi/emsp/2.2.1/commands");
        endpointList.add(endpoint);
        endpoints.setEndpoints(endpointList);
        Response<Endpoints> endpointsResponse = new Response<>();
        endpointsResponse.setData(endpoints);
        return ResponseEntity.ok().body(endpointsResponse);

    }
}
