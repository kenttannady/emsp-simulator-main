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

import com.github.emsp.simulator.model.emsp.Response;
import com.github.emsp.simulator.model.emsp.Version;
import com.github.emsp.simulator.service.JitterSimulatorService;

@RestController
public class VersionController {

    @Autowired
    private JitterSimulatorService service;

    @GetMapping("/ocpi/emsp/2.1.1/versions")
    public ResponseEntity<Response<List<Version>>> getVersion211(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "retry", required = false) Integer retry,
            @RequestParam(name = "timeout", required = false) Integer timeout,
            @RequestParam(name = "uid", required = false) String uid) {

        // validate Token A
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        status = service.simulateJitter("version module", "ocpi v2.1.1", uid, retry, status, timeout);
        if(status != 200){
            return ResponseEntity.status(status).body(null);
        }

        Response<List<Version>> versionResponse = new Response<>();
        List<Version> versions = new ArrayList<>();
        versions.add(new Version("2.1.1", "https://emspsimulator.com/ocpi/emsp/2.1.1/"));
        versions.add(new Version("2.2.1", "https://emspsimulator.com/ocpi/emsp/2.2.1/"));
        versionResponse.setData(versions);
        return ResponseEntity.ok().body(versionResponse);
    }

    @GetMapping("/ocpi/emsp/2.2.1/versions")
    public ResponseEntity<Response<List<Version>>> getVersion221(
            @RequestHeader(name = "Authorization", required = false) String token,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "retry", required = false) Integer retry,
            @RequestParam(name = "timeout", required = false) Integer timeout,
            @RequestParam(name = "uid", required = false) String uid) {

        // validate Token A
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        status = service.simulateJitter("version module", "ocpi v2.1.1", uid, retry, status, timeout);
        if(status != 200){
            return ResponseEntity.status(status).body(null);
        }
        

        Response<List<Version>> versionResponse = new Response<>();
        List<Version> versions = new ArrayList<>();
        versions.add(new Version("2.1.1", "https://emspsimulator.com/ocpi/emsp/2.1.1/"));
        versions.add(new Version("2.2.1", "https://emspsimulator.com/ocpi/emsp/2.2.1/"));
        versionResponse.setData(versions);
        return ResponseEntity.ok().body(versionResponse);
    }
}
