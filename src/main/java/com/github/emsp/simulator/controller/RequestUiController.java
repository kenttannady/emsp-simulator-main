package com.github.emsp.simulator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestUiController {
    

    @GetMapping("/")
    public String get() {
        return "requests";
    }

}
