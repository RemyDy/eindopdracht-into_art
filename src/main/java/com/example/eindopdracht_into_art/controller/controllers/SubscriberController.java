package com.example.eindopdracht_into_art.controller.controllers;

import com.example.eindopdracht_into_art.controller.services.SubscriberService;
import com.example.eindopdracht_into_art.model.dtos.SubscriberDto;
import com.example.eindopdracht_into_art.model.dtos.SubscriberInputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;

import static com.example.eindopdracht_into_art.controller.helpers.Message.*;
import static com.example.eindopdracht_into_art.controller.helpers.Randomizer.createRandomToken;
import static com.example.eindopdracht_into_art.controller.helpers.Validation.validateAndReturnErrorsIfAny;

@RestController
@RequestMapping("into-art/newsletter")
public class SubscriberController {

    private final SubscriberService service;

    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    //region SUBSCRIBE
    @PostMapping("subscribe")
    public ResponseEntity<Object> createSubscriberForNewsLetter(
            @Valid @RequestBody SubscriberInputDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            return validateAndReturnErrorsIfAny(br);
        }
        supplementSubscriberInputDto(dto);

        final var record = service.createSubscriber(dto);
        final var location = URI.create(SUBSCRIBER_CREATED_LOCATION.formatted(record.getId()));
        final var link = createLinkToConfirmEmail(record, SUBSCRIBE);

        return ResponseEntity.created(location).body(link);
    }

    @PutMapping("subscribe/{confirmationToken}")
    public ResponseEntity<String> confirmEmailByTokenToConfirmSubscription(
            @PathVariable("confirmationToken") String token
    ) {
        SubscriberInputDto dto = new SubscriberInputDto();
        dto.setConfirmationToken(token);
        dto.setTokenConfirmedAt(LocalDateTime.now());
        service.confirmSubscription(dto);

        return ResponseEntity.ok().body(SUBSCRIBER_CONFIRMED_SUCCESS_MESSAGE);
    }
    //endregion


    //region UNSUBSCRIBE
    @PostMapping("unsubscribe")
    public ResponseEntity<Object> unsubscribeFromNewsletter(
            @Valid @RequestBody SubscriberInputDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            return validateAndReturnErrorsIfAny(br);
        }
        dto.setTokenConfirmedAt(null);

        final var record = service.sendTokenBeforeDeletion(dto);
        final var location = URI.create(SUBSCRIBER_CREATED_LOCATION.formatted(record.getId()));
        final var link = createLinkToConfirmEmail(record, UNSUBSCRIBE);

        return ResponseEntity.created(location).body(link);
    }

    @DeleteMapping("unsubscribe/{confirmationToken}")
    public ResponseEntity<String> deleteSubscriber(
            @PathVariable("confirmationToken") String token
    ) {
        SubscriberInputDto dto = new SubscriberInputDto();
        dto.setConfirmationToken(token);

        service.deleteSubscriberByToken(dto);

        return ResponseEntity.ok().body(UNSUBSCRIBED_CONFIRMED_MESSAGE);
    }
    //endregion


    //region Internal Methods
    private void supplementSubscriberInputDto(SubscriberInputDto dto) {

        final var token = createRandomToken();
        dto.setConfirmationToken(token);
        dto.setTokenCreatedAt(LocalDateTime.now());
        dto.setTokenExpiredAt(LocalDateTime.now().plusMinutes(15));
    }

    private String createLinkToConfirmEmail(SubscriberDto record, String endpoint) {

        final var link = CONFIRM_EMAIL_LINK.formatted(
                endpoint, record.getConfirmationToken()
        );

        return switch (endpoint) {
            case SUBSCRIBE -> SUBSCRIBER_CONFIRM_EMAIL_MESSAGE.formatted(record.getName(), link);
            case UNSUBSCRIBE -> UNSUBSCRIBE_CONFIRM_EMAIL_MESSAGE.formatted(record.getName(), link);
            default -> throw new IllegalArgumentException();
        };
    }
    //endregion
}
