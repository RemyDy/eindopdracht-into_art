package com.eindopdracht_into_art.controller.services;

import com.eindopdracht_into_art.model.dtos.SubscriberDto;
import com.eindopdracht_into_art.model.dtos.SubscriberInputDto;
import com.eindopdracht_into_art.model.exceptions.TokenNotValidException;
import com.eindopdracht_into_art.controller.repositories.SubscriberRepository;
import com.eindopdracht_into_art.controller.services.interfaces.SubscriberService;
import com.eindopdracht_into_art.model.entities.Subscriber;
import com.eindopdracht_into_art.model.exceptions.PreConditionNotMetException;
import com.eindopdracht_into_art.model.exceptions.RecordExistsException;
import com.eindopdracht_into_art.model.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.eindopdracht_into_art.controller.helpers.Message.*;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    // TODO: Na school project zoek uit hoe een mail service opgezet kan worden

    private final SubscriberRepository repository;

    public SubscriberServiceImpl(SubscriberRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubscriberDto createSubscriber(SubscriberInputDto dto) {performChecksOnDtoBeforeSavingSubscriber(dto);
        final var savedSubscriber = repository.save(relayToSubscriber(dto));
        return relayToDto(savedSubscriber);
    }

    @Override
    public void confirmSubscription(SubscriberInputDto dto) {
        final var subscriber = performChecksOnDtoBeforeConfirmingSubscription(dto);
        repository.save(subscriber);
    }

    @Override
    public SubscriberDto sendTokenBeforeDeletion(SubscriberInputDto dto) {
        final var subscriber = performChecksBeforeSendingUnsubscribeToken(dto);
        return relayToDto(subscriber);
    }


    @Override
    public void deleteSubscriberByToken(SubscriberInputDto dto) {
        final var subscriber = performChecksBeforeDeletingSubscription(dto);
        repository.delete(subscriber);
    }

    //region private methods
    private SubscriberDto relayToDto(Subscriber subscriber) {
        var dto = new SubscriberDto();
        dto.setId(subscriber.getId());
        dto.setName(subscriber.getName());
        dto.setEmail(subscriber.getEmail());
        dto.setConfirmationToken(subscriber.getConfirmationToken());

        return dto;
    }

    private Subscriber relayToSubscriber(SubscriberInputDto dto) {
        final var subscriber = new Subscriber.Builder(
                dto.getEmail(),
                dto.getConfirmationToken(),
                dto.getTokenCreatedAt(),
                dto.getTokenExpiredAt()
        );
        subscriber.name(dto.getName());

        return subscriber.build();
    }

    private void performChecksOnDtoBeforeSavingSubscriber(SubscriberInputDto dto) {
        if (repository.findSubscriberByEmail(dto.getEmail()).isPresent()) {
            final var subscriber
                    = repository.findSubscriberByEmail(dto.getEmail()).get();

            if (subscriber.getTokenConfirmedAt() != null) {
                throw new RecordExistsException(SUBSCRIBED_CONFIRMED_MESSAGE);
            }
            if (subscriber.getTokenConfirmedAt() == null
                && LocalDateTime.now().isBefore(subscriber.getTokenExpiredAt())
            ) {
                throw new PreConditionNotMetException(SUBSCRIBED_UNCONFIRMED_MESSAGE);
            }
            if (subscriber.getTokenConfirmedAt() == null
                && LocalDateTime.now().isAfter(subscriber.getTokenExpiredAt())) {
                repository.delete(subscriber);
            }
        }
    }

    private Subscriber performChecksOnDtoBeforeConfirmingSubscription(SubscriberInputDto dto) {

        final var subscriber
                = repository.findSubscriberByConfirmationToken(dto.getConfirmationToken())
                .orElseThrow(RecordNotFoundException::new);

        if (subscriber.getTokenConfirmedAt() != null) {
            throw new RecordExistsException(SUBSCRIBED_CONFIRMED_MESSAGE);
        }
        if (LocalDateTime.now().isAfter(subscriber.getTokenExpiredAt())) {
            throw new TokenNotValidException(TOKEN_EXPIRED_MESSAGE + SUBSCRIBE_AGAIN_MESSAGE);
        }
        subscriber.setTokenConfirmedAt(dto.getTokenConfirmedAt());

        return subscriber;
    }

    private Subscriber performChecksBeforeSendingUnsubscribeToken(SubscriberInputDto dto) {
        return repository.findSubscriberByEmail(dto.getEmail())
                .orElseThrow(RecordNotFoundException::new);
    }

    private Subscriber performChecksBeforeDeletingSubscription(SubscriberInputDto dto) {
        return repository.findSubscriberByConfirmationToken(dto.getConfirmationToken())
                .orElseThrow(RecordNotFoundException::new);
    }
    //endregion
}