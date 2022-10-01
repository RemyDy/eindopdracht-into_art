package com.eindopdracht_into_art.services.interfaces;

import com.eindopdracht_into_art.models.dtos.SubscriberDto;
import com.eindopdracht_into_art.models.dtos.SubscriberInputDto;


public interface SubscriberService {

    SubscriberDto createSubscriber(SubscriberInputDto dto);

    SubscriberDto confirmSubscription(Long id, SubscriberInputDto dto);

    void deleteSubscriberByEmail(SubscriberInputDto dto);

}
