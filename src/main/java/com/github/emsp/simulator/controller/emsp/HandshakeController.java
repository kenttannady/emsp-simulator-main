package com.github.emsp.simulator.controller.emsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.emsp.simulator.model.common.CredentialResponse;
import com.github.emsp.simulator.model.common.EndpointResponse;
import com.github.emsp.simulator.model.common.HandshakeRequest;
import com.github.emsp.simulator.model.common.HandshakeResponse;
import com.github.emsp.simulator.model.common.SimulationStep;
import com.github.emsp.simulator.model.common.VersionResponse;
import com.github.emsp.simulator.model.emsp.BusinessDetail;
import com.github.emsp.simulator.model.emsp.Credential221;
import com.github.emsp.simulator.model.emsp.Endpoint;
import com.github.emsp.simulator.model.emsp.Endpoints;
import com.github.emsp.simulator.model.emsp.Logo;
import com.github.emsp.simulator.model.emsp.Response;
import com.github.emsp.simulator.model.emsp.Role;
import com.github.emsp.simulator.model.emsp.Version;

@RestController
@RequestMapping("/api")
public class HandshakeController {

    private RestClient restClient = RestClient.create();
    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/handshake")
    public ResponseEntity<HandshakeResponse> postHandShake(
            @RequestBody HandshakeRequest request,
            @RequestHeader(name = "Authorization", required = false) String token) {
        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        // steps to validate hand shake process
        List<SimulationStep> steps = new ArrayList<>();
        // 1. Invoke CPO version URL with Token A as authorization parameter
        SimulationStep step1 = new SimulationStep();
        step1.setNumber(1);
        step1.setText("Invoking CPO version URL " + request.getUrl() + " with token " + request.getTokenA());
        steps.add(step1);
        Response<List<Version>> step1Response = null;
        try {
            step1Response = restClient.get()
                    .uri(request.getUrl())
                    .header("Authorization", "Token " + request.getTokenA())
                    .header("Content-Type", "application/json")
                    .retrieve()
                    .body(new VersionResponse());
            step1.setStatus("OK");
            step1.setMessage("Success");
            step1.setData(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(step1Response));
        } catch (Exception ex) {
            step1.setStatus("ERROR");
            step1.setMessage(ex.getMessage());
            step1.setData(ex.getMessage());
        }
        if (step1Response != null) {
            // 2. Selecting highest version
            SimulationStep step2 = new SimulationStep();
            step2.setNumber(2);
            step2.setText("Selecting CPO higest version");
            steps.add(step2);
            if (step1Response.getData() != null && !step1Response.getData().isEmpty()) {
                List<Version> sortedVersions = new ArrayList<>(step1Response.getData());
                Collections.sort(sortedVersions);
                Version latestVersion = sortedVersions.get(0);
                step2.setStatus("OK");
                step2.setMessage("Success");
                step2.setData("OCPI v" + latestVersion.getVersion());
                // 3. Invoke Endpoints CPO url to get credentials API URL
                SimulationStep step3 = new SimulationStep();
                step3.setNumber(3);
                step3.setText("Invoke Endpoints CPO URL " + latestVersion.getUrl() + " with token " + request.getTokenA() + " to get credentials API URL");
                steps.add(step3);
                Response<Endpoints> step3Response = null;
                try {
                    step3Response = restClient.get()
                            .uri(latestVersion.getUrl())
                            .header("Authorization", "Token " + request.getTokenA())
                            .header("Content-Type", "application/json")
                            .retrieve()
                            .body(new EndpointResponse());
                    step3.setStatus("OK");
                    step3.setMessage("Success");
                    step3.setData(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(step3Response));
                } catch (Exception ex) {
                    step3.setStatus("ERROR");
                    step3.setMessage(ex.getMessage());
                    step3.setData(ex.getMessage());
                }
                if (step3Response != null) {
                    // 4. Invoke POST Credential CPO url to send Token B and eMSP's endpoints URL
                    SimulationStep step4 = new SimulationStep();
                    step4.setNumber(4);
                    steps.add(step4);
                    // find credentials API url
                    Endpoint credentialEndpoint = null;
                    if (step3Response.getData() != null && step3Response.getData().getEndpoints() != null
                            && !step3Response.getData().getEndpoints().isEmpty()) {
                        List<Endpoint> endpoints = step3Response.getData().getEndpoints();
                        for (Endpoint ep : endpoints) {
                            if (ep.getIdentifier().equalsIgnoreCase("credentials")) {
                                credentialEndpoint = ep;
                                break;
                            }
                        }
                    }
                    if (credentialEndpoint != null) {
                        Response<Credential221> step4Response = null;
                        step4.setText("Invoke POST Credential CPO URL " + credentialEndpoint.getUrl() + " with token "
                                + request.getTokenA());
                        Credential221 requestBody = new Credential221();
                        requestBody.setToken(UUID.randomUUID().toString());
                        requestBody.setUrl("https://emspsimulator.com/ocpi/emsp/2.2.1/versions");
                        Role role = new Role();
                        role.setCountryCode("NL");
                        role.setPartyId("ESR");
                        role.setRole("EMSP");
                        BusinessDetail bd = new BusinessDetail();
                        Logo logo = new Logo();
                        logo.setCategory("LOCATION");
                        logo.setHeight(100);
                        logo.setWidth(100);
                        logo.setThumbnail("https://emspsimulator.com/thumb.jpg");
                        logo.setType("jpg");
                        logo.setUrl("https://emspsimulator.com/logo.jpg");
                        bd.setLogo(logo);
                        bd.setName("eMSP Simulator");
                        bd.setWebsite("https://emspsimulator.com/");
                        role.setBusinessDetails(bd);
                        List<Role> roles = new ArrayList<>();
                        roles.add(role);
                        requestBody.setRoles(roles);
                        try {
                            step4.setData("Request Body:\n" +
                                    mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody));
                        } catch (Exception ex) {
                            // intentionally left blank
                        }
                        try {
                            step4Response = restClient.post()
                                    .uri(credentialEndpoint.getUrl())
                                    .header("Authorization", "Token " + request.getTokenA())
                                    .header("Content-Type", "application/json")
                                    .body(requestBody)
                                    .retrieve()
                                    .body(new CredentialResponse());
                            step4.setStatus("OK");
                            step4.setMessage("Success");
                            step4.setData(step4.getData() + "\nResponse Body:\n"
                                    + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(step4Response));
                        } catch (Exception ex) {
                            step4.setStatus("ERROR");
                            step4.setMessage(ex.getMessage());
                            step4.setData(step4.getData() + "\nError Message: " + ex.getMessage());
                        }
                    } else {
                        step4.setStatus("ERROR");
                        step4.setText("Invoke POST Credential CPO url");
                        step4.setMessage("Cannot find credentials endpoint url");
                        step4.setData("Cannot find credentials endpoint url");
                    }
                }
            } else {
                step2.setStatus("ERROR");
                step2.setMessage("No version listed in response");
                step2.setData("OCPI unknown version");
            }
        }

        HandshakeResponse handshakeResponse = HandshakeResponse.ok();
        handshakeResponse.setSteps(steps);
        return ResponseEntity.ok().body(handshakeResponse);
    }

}
