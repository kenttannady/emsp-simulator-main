package com.github.emsp.simulator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.emsp.simulator.entity.Request;
import com.github.emsp.simulator.repository.RequestRepository;

@RestController
@RequestMapping("/api")
public class RequestRestController {

    @Autowired
    private RequestRepository repository;

    @GetMapping("/requests")
    public List<Request> getAll() {
        return repository.findByOrderByDateDesc();
    }

    @DeleteMapping("/requests")
    public void deleteAll() {
        repository.deleteAll();
    }

}
