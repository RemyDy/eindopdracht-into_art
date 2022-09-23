package com.eindopdracht_into_art.controller.services;

import com.eindopdracht_into_art.controller.repositories.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String getGreeting(String name) {

        return "Hello %s".formatted(name);
    }
}
