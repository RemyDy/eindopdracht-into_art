package com.eindopdracht_into_art.controller.services.interfaces;

import com.eindopdracht_into_art.model.dtos.SubscriberDto;
import com.eindopdracht_into_art.model.dtos.SubscriberInputDto;


public interface SubscriberService {

    SubscriberDto createSubscriber(SubscriberInputDto dto);

    void confirmSubscription(SubscriberInputDto dto);

    void deleteSubscriberByToken(SubscriberInputDto dto);

    SubscriberDto sendTokenBeforeDeletion(SubscriberInputDto dto);
}
