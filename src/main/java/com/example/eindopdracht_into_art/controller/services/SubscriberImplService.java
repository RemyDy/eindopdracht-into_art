package com.example.eindopdracht_into_art.controller.services;

import com.example.eindopdracht_into_art.controller.repositories.SubscriberRepository;
import com.example.eindopdracht_into_art.model.dtos.SubscriberInputDto;
import com.example.eindopdracht_into_art.model.dtos.SubscriberDto;
import com.example.eindopdracht_into_art.model.entities.Subscriber;
import com.example.eindopdracht_into_art.model.exceptions.PreConditionNotMetException;
import com.example.eindopdracht_into_art.model.exceptions.RecordExistsException;
import com.example.eindopdracht_into_art.model.exceptions.RecordNotFoundException;
import com.example.eindopdracht_into_art.model.exceptions.TokenNotValidException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.eindopdracht_into_art.controller.helpers.Message.*;

@Service
public class SubscriberImplService implements SubscriberService {
    // TODO: Na school project zoek uit hoe een mail service opgezet kan worden

    private final SubscriberRepository repository;

    public SubscriberImplService(SubscriberRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubscriberDto createSubscriber(SubscriberInputDto dto) {
        performChecksOnDtoBeforeSavingSubscriber(dto);

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
            SubscriberInputDto dto
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

    private void performChecksOnDtoBeforeSavingSubscriber(SubscriberInputDto dto) {

        if (repository.findSubscriberByEmail(dto.getEmail()).isPresent()) {

            final var subscriber = repository.findSubscriberByEmail(dto.getEmail()).get();

            // if email is already confirmed throw exception
            if (subscriber.getTokenConfirmedAt() != null) {
                throw new RecordExistsException(SUBSCRIBED_CONFIRMED_MESSAGE);
            }

            // if email is not confirmed and confirmation token is not expired throw exception
            if (subscriber.getTokenConfirmedAt() == null
                && LocalDateTime.now().isBefore(subscriber.getTokenExpiredAt())
            ) {
                throw new PreConditionNotMetException(SUBSCRIBED_UNCONFIRMED_MESSAGE);
            }

            // if email is not confirmed and confirmation token is expired delete subscriber
            if (subscriber.getTokenConfirmedAt() == null
                && LocalDateTime.now().isAfter(subscriber.getTokenExpiredAt())) {
                repository.delete(subscriber);
            }
        }
    }

    private Subscriber performChecksOnDtoBeforeConfirmingSubscription(SubscriberInputDto dto) {

        final var subscriber = repository.findSubscriberByConfirmationToken(dto.getConfirmationToken())
                .orElseThrow(RecordNotFoundException::new);

        // if subscriber already confirmed subscription throw exception
        if (subscriber.getTokenConfirmedAt() != null) {
            throw new RecordExistsException(SUBSCRIBED_CONFIRMED_MESSAGE);
        }

        // if confirmation token is expired throw exception
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