package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.helpers.Randomizer;
import com.eindopdracht_into_art.models.dtos.SubscriberInputDto;
import com.eindopdracht_into_art.payload.SubscriberResponse;
import com.eindopdracht_into_art.services.interfaces.SubscriberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

import static com.eindopdracht_into_art.config.security.SecurityConstants.EXPIRE_MIN_15;
import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpoint.EP_NEWSLETTER;
import static com.eindopdracht_into_art.helpers.Validator.validateAndReturnErrors;


@RestController
@RequestMapping(EP_NEWSLETTER)
public class SubscriberController {

    private final SubscriberService subscriberService;

    public SubscriberController(SubscriberService subscriberService) {
        this.subscriberService = subscriberService;
    }


    @PostMapping()
    public ResponseEntity<Object> createSubscriberForNewsLetter(
            @Valid @RequestBody SubscriberInputDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            return validateAndReturnErrors(br);
        }
        generateTokenAndSetToDto(dto);
        SubscriberResponse record = new SubscriberResponse(subscriberService.subscribeToNewsLetter(dto));
//        subscriberService.addSubscription(record.subscriber().getId(), record.subscriber().getNewsletter());

        return ResponseEntity.created(record.getLocation()).body(record.getConfirmTokenMessage());
    }

    @PutMapping("/{id}/{confirmationToken}")
    public ResponseEntity<String> confirmEmailByTokenToConfirmSubscription(
            @PathVariable Long id,
            @PathVariable() String confirmationToken
    ) {
        final var dto = setConfirmedTokenToDto(confirmationToken);
        SubscriberResponse record = new SubscriberResponse(subscriberService.confirmEmail(id, dto));

        return ResponseEntity.ok().body(record.getSubscriberConfirmedMessage());
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteSubscriber(
            @Valid @RequestBody SubscriberInputDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            validateAndReturnErrors(br);
        }
        subscriberService.deleteSubscriberByEmail(dto);

        return ResponseEntity.noContent().build();
    }

    //region Internal Methods
    private void generateTokenAndSetToDto(
            SubscriberInputDto dto
    ) {
        final var token = Randomizer.createRandomUUIDToken();
        dto.setConfirmationToken(token);
        dto.setTokenCreatedAt(LocalDateTime.now());
        dto.setTokenExpiredAt(LocalDateTime.now().plusMinutes(EXPIRE_MIN_15));
    }

    private static SubscriberInputDto setConfirmedTokenToDto(String confirmationToken) {
        final var dto = new SubscriberInputDto();
        dto.setConfirmationToken(confirmationToken);
        dto.setTokenConfirmedAt(LocalDateTime.now());
        return dto;
    }
    //endregion
}
