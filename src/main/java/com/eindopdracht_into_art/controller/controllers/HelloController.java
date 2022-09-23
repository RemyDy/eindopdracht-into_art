package com.eindopdracht_into_art.controller.controllers;

import com.eindopdracht_into_art.controller.repositories.HelloService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.eindopdracht_into_art.controller.helpers.GlobalConstant.EP_HELLO;


@RestController
public class HelloController {

    private final HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping(EP_HELLO)
    public ResponseEntity<String> helloWorld(
            @RequestParam(defaultValue = "World!") String name
    ) {
        final var authenticated = SecurityContextHolder
                .getContext()
                .getAuthentication();

        final var greeting = service.getGreeting(name);

        return ResponseEntity.ok().header(authenticated.getName()).body(greeting);
    }

}
