package com.github.emsp.simulator.controller.emsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HandshakeUiController {
    
     @GetMapping("/handshake")
    public String get() {
        return "handshake";
    }

}
