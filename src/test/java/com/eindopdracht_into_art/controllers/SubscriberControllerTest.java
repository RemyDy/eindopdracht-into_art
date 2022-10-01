package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.config.ConfigSpringBootTest;
import com.eindopdracht_into_art.models.entities.Subscriber;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SubscriberControllerTest extends ConfigSpringBootTest {

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