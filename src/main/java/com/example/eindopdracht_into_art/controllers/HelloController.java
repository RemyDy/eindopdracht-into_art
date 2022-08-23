package com.example.eindopdracht_into_art.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public ResponseEntity<String> hello(@RequestParam(defaultValue = "World!") String name) {

        return ResponseEntity.ok().body("Hello %s".formatted(name));

    }

}
