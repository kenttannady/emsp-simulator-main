package com.github.emsp.simulator.model.common;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;

import com.github.emsp.simulator.model.emsp.Response;
import com.github.emsp.simulator.model.emsp.Version;

public class VersionResponse extends ParameterizedTypeReference<Response<List<Version>>>{
    
}
