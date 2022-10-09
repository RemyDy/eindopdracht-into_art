package com.eindopdracht_into_art.services.interfaces;

import com.eindopdracht_into_art.models.dtos.SubscriberDto;
import com.eindopdracht_into_art.models.dtos.SubscriberInputDto;


public interface SubscriberService {

    SubscriberDto subscribeToNewsLetter(SubscriberInputDto dto);

    SubscriberDto confirmEmail(Long id, SubscriberInputDto dto);

    void deleteSubscriberByEmail(SubscriberInputDto dto);
}
