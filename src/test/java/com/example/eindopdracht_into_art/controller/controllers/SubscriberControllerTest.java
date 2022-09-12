package com.example.eindopdracht_into_art.controller.controllers;

import com.example.eindopdracht_into_art.controller.services.SubscriberService;
import com.example.eindopdracht_into_art.model.entities.Subscriber;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

@WebMvcTest(SubscriberController.class)
class SubscriberControllerTest {

    //region Test Setup
    @Autowired
    MockMvc mockMvc;

    @MockBean
    SubscriberService service;
    //endregion


    @Test
    void createSubscriber() throws Exception {
        final var subscriber = new Subscriber.Builder(
                "test@test.nl",
                "1",
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(1)
                );
        subscriber.name(null);
        subscriber.build();
    }

    @Test
    void unSubscribe() throws Exception {
    }

    @Test
    void messageConfirmEmail() {
    }

    @Test
    void confirmEmailAdressByToken() {
    }
}