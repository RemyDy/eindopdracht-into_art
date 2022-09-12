package com.example.eindopdracht_into_art.controller.controllers;

import com.example.eindopdracht_into_art.controller.repositories.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<String> hello(
            @RequestParam(defaultValue = "World!") String name
    ) {
        final var greeting = service.getGreeting(name);

        return ResponseEntity.ok().body(greeting);
    }
}
