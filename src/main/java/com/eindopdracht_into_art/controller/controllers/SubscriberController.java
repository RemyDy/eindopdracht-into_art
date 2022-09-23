package com.eindopdracht_into_art.controller.controllers;

import com.eindopdracht_into_art.controller.helpers.Message;
import com.eindopdracht_into_art.controller.helpers.Randomizer;
import com.eindopdracht_into_art.controller.helpers.Validator;
import com.eindopdracht_into_art.controller.services.interfaces.SubscriberService;
import com.eindopdracht_into_art.model.dtos.SubscriberDto;
import com.eindopdracht_into_art.model.dtos.SubscriberInputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static com.eindopdracht_into_art.controller.helpers.GlobalConstant.EP_NEWSLETTER;
import static com.eindopdracht_into_art.controller.helpers.UriCreator.createUriById;


@RestController
public class SubscriberController {

    private final String subscribeEndpoint = "/subscribe";
    private final String unsubscribeEndpoint = "/unsubscribe";
    private final SubscriberService service;


    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    //region SUBSCRIBE

    @PostMapping(EP_NEWSLETTER)
    public ResponseEntity<Object> createSubscriberForNewsLetter(
            @Valid @RequestBody SubscriberInputDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            return Validator.validateAndReturnErrorsIfAny(br);
        }
        generateTokenAddToSubscriberInputDto(dto);

        final var record = service.createSubscriber(dto);

        final var location = createUriById(subscribeEndpoint, record.getId());

        final var link = getPersonalMessageWithLinkToConfirmEmail(
                record, String.valueOf(location), subscribeEndpoint
        );
        return ResponseEntity.created(location).body(link);
    }

    @PutMapping(EP_NEWSLETTER + "/{confirmationToken}")
    public ResponseEntity<String> confirmEmailByTokenToConfirmSubscription(
            @PathVariable() String confirmationToken
    ) {
        SubscriberInputDto dto = new SubscriberInputDto();
        dto.setConfirmationToken(confirmationToken);
        dto.setTokenConfirmedAt(LocalDateTime.now());

        service.confirmSubscription(dto);

        return ResponseEntity.ok().body(Message.SUBSCRIBER_CONFIRMED_SUCCESS_MESSAGE);
    }
    //endregion


    //region UNSUBSCRIBE
    @PostMapping(unsubscribeEndpoint)
    public ResponseEntity<Object> unsubscribeFromNewsletter(
            @Valid @RequestBody SubscriberInputDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            return Validator.validateAndReturnErrorsIfAny(br);
        }
        dto.setTokenConfirmedAt(null);

        final var record = service.sendTokenBeforeDeletion(dto);

        final var location = createUriById(unsubscribeEndpoint, record.getId());

        final var link = getPersonalMessageWithLinkToConfirmEmail(
                record, String.valueOf(location), unsubscribeEndpoint
        );
        return ResponseEntity.created(location).body(link);
    }

    @DeleteMapping(unsubscribeEndpoint + "/{confirmationToken}")
    public ResponseEntity<String> deleteSubscriber(
            @PathVariable() String confirmationToken
    ) {
        SubscriberInputDto dto = new SubscriberInputDto();
        dto.setConfirmationToken(confirmationToken);

        service.deleteSubscriberByToken(dto);

        return ResponseEntity.ok().body(Message.UNSUBSCRIBED_CONFIRMED_MESSAGE);
    }
    //endregion


    //region Internal Methods
    private void generateTokenAddToSubscriberInputDto(
            SubscriberInputDto dto
    ) {
        final var token = Randomizer.createRandomToken();
        dto.setConfirmationToken(token);
        dto.setTokenCreatedAt(LocalDateTime.now());
        dto.setTokenExpiredAt(LocalDateTime.now().plusMinutes(15));
    }

    private String getPersonalMessageWithLinkToConfirmEmail(
            SubscriberDto record,
            String location,
            String mapping
    ) {
        final var link = ("%s/%s").formatted(location, record.getConfirmationToken());

        return switch (mapping) {
            case subscribeEndpoint -> Message.SUBSCRIBER_CONFIRM_EMAIL_MESSAGE.formatted(record.getName(), link);
            case unsubscribeEndpoint -> Message.UNSUBSCRIBE_CONFIRM_EMAIL_MESSAGE.formatted(record.getName(), link);
            default -> throw new IllegalArgumentException();
        };
    }
    //endregion
}
