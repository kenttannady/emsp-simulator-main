package com.github.emsp.simulator.controller.emsp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.emsp.simulator.entity.Request;
import com.github.emsp.simulator.model.emsp.BusinessDetail;
import com.github.emsp.simulator.model.emsp.Credential211;
import com.github.emsp.simulator.model.emsp.Credential221;
import com.github.emsp.simulator.model.emsp.Logo;
import com.github.emsp.simulator.model.emsp.Response;
import com.github.emsp.simulator.model.emsp.ResponseNoData;
import com.github.emsp.simulator.model.emsp.Role;
import com.github.emsp.simulator.repository.RequestRepository;

@RestController
public class CredentialController {

    @Autowired
    private RequestRepository repository;

    private ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/ocpi/emsp/2.1.1/credentials")
    public ResponseEntity<ResponseNoData> getCredentials211(@RequestHeader(name = "Authorization", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Request request = new Request();
        request.setData("token: " + token);
        request.setDate(new Date());
        request.setModule("credential module");
        request.setParty("eMSP");
        request.setUid(token);
        request.setVersion("ocpi v2.1.1");
        repository.save(request);
        ResponseNoData response = new ResponseNoData();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/ocpi/emsp/2.1.1/credentials")
    public ResponseEntity<Response<Credential211>> putCredentials211(
            @RequestBody Credential211 credential,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return processCredentials211(credential, token, "PUT");
    }

    @PostMapping("/ocpi/emsp/2.1.1/credentials")
    public ResponseEntity<Response<Credential211>> postCredentials211(
            @RequestBody Credential211 credential,
            @RequestHeader(name = "Authorization", required = false) String token) {

        return processCredentials211(credential, token, "POST");
    }

    public ResponseEntity<Response<Credential211>> processCredentials211(Credential211 credential, String token, String method){
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Response<Credential211> r = new Response<>();

        Credential211 c = new Credential211();
        c.setToken(UUID.randomUUID().toString());
        c.setUrl(("https://emspsimulator.com/ocpi/emsp/2.1.1/versions"));
        c.setCountryCode("NL");
        c.setPartyId("ESR");

        BusinessDetail bd = new BusinessDetail();
        bd.setName("EMSP Simulator");
        bd.setWebsite("https://emspsimulator.com");
        Logo logo = new Logo();
        logo.setUrl("https://emspsimulator.com/logo.jpg");
        logo.setThumbnail("https://emspsimulator.com/logo-thumbnail.jpg");
        logo.setWidth(400);
        logo.setHeight(400);
        logo.setCategory("OTHER");
        logo.setType("jpg");
        bd.setLogo(logo);
        c.setBusinessDetails(bd);

        r.setData(c);

        Request request = new Request();
        try{
            request.setData(method + ": " + mapper.writeValueAsString(credential));
            request.setDate(new Date());
            request.setModule("credential module");
            request.setParty("eMSP");
            request.setUid(token);
            request.setVersion("ocpi v2.1.1");
            repository.save(request);
        } catch(Exception ex){
            //intentionally left blank
        }

        return ResponseEntity.ok().body(r);
    }

    @GetMapping("/ocpi/emsp/2.2.1/credentials")
    public ResponseEntity<ResponseNoData> getCredentials221(@RequestHeader(name = "Authorization", required = false) String token) {

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        ResponseNoData response = new ResponseNoData();
        Request request = new Request();
        request.setData("token: " + token);
        request.setDate(new Date());
        request.setModule("credential module");
        request.setParty("eMSP");
        request.setUid(token);
        request.setVersion("ocpi v2.2.1");
        repository.save(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/ocpi/emsp/2.2.1/credentials")
    public ResponseEntity<Response<Credential221>> putCredentials221(
            @RequestBody Credential221 credential,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return processCredentials221(credential, token, "PUT");
    }
    
    @PostMapping("/ocpi/emsp/2.2.1/credentials")
    public ResponseEntity<Response<Credential221>> postCredentials221(
            @RequestBody Credential221 credential,
            @RequestHeader(name = "Authorization", required = false) String token) {
        return processCredentials221(credential, token, "POST");
    }

    public ResponseEntity<Response<Credential221>> processCredentials221(Credential221 credential, String token, String method){
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        Response<Credential221> response = new Response<>();

        Credential221 c = new Credential221();
        c.setToken(UUID.randomUUID().toString());
        c.setUrl(("https://emspsimulator.com/ocpi/emsp/2.2.1/versions"));

        List<Role> roles = new ArrayList<>();
        c.setRoles(roles);
        Role r = new Role();
        r.setCountryCode("NL");
        r.setPartyId("ESR");
        r.setRole("EMSP");

        BusinessDetail bd = new BusinessDetail();
        bd.setName("EMSP Simulator");
        bd.setWebsite("https://emspsimulator.com");
        r.setBusinessDetails(bd);

        Logo logo = new Logo();
        logo.setUrl("https://emspsimulator.com/logo.jpg");
        logo.setThumbnail("https://emspsimulator.com/logo-thumbnail.jpg");
        logo.setWidth(400);
        logo.setHeight(400);
        logo.setCategory("OTHER");
        logo.setType("jpg");
        bd.setLogo(logo);
        roles.add(r);

        Request request = new Request();
        try{
            request.setData(method + ": " + mapper.writeValueAsString(credential));
            request.setDate(new Date());
            request.setModule("credential module");
            request.setParty("eMSP");
            request.setUid(token);
            request.setVersion("ocpi v2.2.1");
            repository.save(request);
        } catch(Exception ex){
            //intentionally left blank
        }

        response.setData(c);
        return ResponseEntity.ok().body(response);
    }

}
