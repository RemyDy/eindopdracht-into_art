package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.services.interfaces.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String getGreeting(String name) {

        return "Hello %s".formatted(name);
    }

}
