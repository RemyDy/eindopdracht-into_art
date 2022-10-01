package com.eindopdracht_into_art.services;

import com.eindopdracht_into_art.models.dtos.SubscriberDto;
import com.eindopdracht_into_art.models.dtos.SubscriberInputDto;
import com.eindopdracht_into_art.models.entities.Subscriber;
import com.eindopdracht_into_art.models.exceptions.PreConditionNotMetException;
import com.eindopdracht_into_art.models.exceptions.RecordExistsException;
import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.models.exceptions.TokenNotValidException;
import com.eindopdracht_into_art.repositories.SubscriberRepository;
import com.eindopdracht_into_art.services.interfaces.SubscriberService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubscriberServiceImpl implements SubscriberService {
    // TODO: Na school project zoek uit hoe een mail service opgezet kan worden

    private final SubscriberRepository repository;

    public SubscriberServiceImpl(SubscriberRepository repository) {
        this.repository = repository;
    }

    @Override
    public SubscriberDto createSubscriber(SubscriberInputDto dto) {
        performChecksOnDtoBeforeSavingSubscriber(dto);
        final var savedSubscriber = repository.save(relayToSubscriber(dto));
        return relayToDto(savedSubscriber);
    }

    @Override
    public SubscriberDto confirmSubscription(Long id, SubscriberInputDto dto) {
        repository.findById(id).orElseThrow(RecordNotFoundException::new);

        final var subscriber = performChecksOnDtoBeforeConfirmingSubscription(dto);
        final var confirmedSubscriber = repository.save(subscriber);
        return relayToDto(confirmedSubscriber);
    }

    @Override
    public void deleteSubscriberByEmail(SubscriberInputDto dto) {
        final var subscriber = repository.findSubscriberByEmail(dto.getEmail())
                .orElseThrow(RecordNotFoundException::new);
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
                throw new RecordExistsException("Dit mail adres is al ingeschreven voor de nieuwsbrief");
            }
            if (subscriber.getTokenConfirmedAt() == null
                && LocalDateTime.now().isBefore(subscriber.getTokenExpiredAt())
            ) {
                throw new PreConditionNotMetException("""
                        Je bent al ingeschreven voor de nieuwsbrief maar je mail adres is nog niet bevestigd.
                        Bij je inschrijving op de nieuwsbrief is een link naar je toegestuurd, deze is 15 minuten geldig.
                        KLik op de link om je e-mail adres alsnog te bevestigen.
                        Kan je de link niet vinden wacht totdat de link is verlopen om je opnieuw in te schrijven
                        """);
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
            throw new RecordExistsException("Dit mail adres is al ingeschreven voor de nieuwsbrief");
        }
        if (LocalDateTime.now().isAfter(subscriber.getTokenExpiredAt())) {
            throw new TokenNotValidException("""
                        Geldigheidsduur van de token is verlopen.
                        Schrijf je opnieuw in en bevestig je e-mail adres binnen 15 minuten om de nieuwsbrief te ontvangen.
                    """);
        }
        subscriber.setTokenConfirmedAt(dto.getTokenConfirmedAt());
        return subscriber;
    }

    //endregion
}