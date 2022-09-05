package com.example.eindopdracht_into_art.controller.services;

import com.example.eindopdracht_into_art.model.dtos.SubscriberDto;
import com.example.eindopdracht_into_art.model.dtos.SubscriberDtoInput;

public interface SubscriberService {
    SubscriberDto createSubscriberRecord(SubscriberDtoInput dto);

    void confirmSubscriptionByConfirmationToken(String token);
}
