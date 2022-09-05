package com.example.eindopdracht_into_art.controller.services;

import com.example.eindopdracht_into_art.controller.repositories.SubscriberRepository;
import com.example.eindopdracht_into_art.model.dtos.SubscriberDto;
import com.example.eindopdracht_into_art.model.dtos.SubscriberDtoInput;
import com.example.eindopdracht_into_art.model.entities.Subscriber;
import com.example.eindopdracht_into_art.model.exceptions.RecordExistsException;

import com.example.eindopdracht_into_art.model.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscriberImplService implements SubscriberService {
    private final SubscriberRepository repository;

    public SubscriberImplService(SubscriberRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubscriberDto createSubscriberRecord(SubscriberDtoInput dto) {
        if (
                repository.findSubscriberByEmail(dto.getEmail()).isPresent()
        ) {
            throw new RecordExistsException(dto.getEmail());
        }

        final var savedSubscriber = repository.save(relayToSubscriber(dto));

        System.out.println(savedSubscriber.getConfirmationToken());

        // TODO: Na school project zoek uit hoe een mail service opgezet kan worden

        return relayToDto(savedSubscriber);
    }

    @Override
    public void confirmSubscriptionByConfirmationToken(String confirmationToken) {

        final var tokenExpiredAt = repository.findSubscriberByConfirmationToken(confirmationToken);

        if (tokenExpiredAt.isEmpty()) {
            throw new RecordNotFoundException("Email confirmation token");
        }

        final var tokenConfirmedAt = LocalDateTime.now();
        System.out.println(tokenConfirmedAt);
        System.out.println(tokenExpiredAt);
    }

    private SubscriberDto relayToDto(
            Subscriber subscriber
    ) {
        var dto = new SubscriberDto();
        dto.setId(subscriber.getId());
        dto.setName(subscriber.getName());
        dto.setEmail(subscriber.getEmail());
        dto.setConfirmationToken(subscriber.getConfirmationToken());

        return dto;
    }

    private Subscriber relayToSubscriber(
            SubscriberDtoInput dto
    ) {
        final var subscriber = new Subscriber.Builder(
                dto.getEmail(),
                dto.getConfirmationToken(),
                dto.getTokenCreatedAt(),
                dto.getTokenExpiredAt()
        );
        subscriber.name(dto.getName());

        return subscriber.build();
    }
}
