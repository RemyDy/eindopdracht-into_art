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
import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpointConstants.EP_NEWSLETTER;
import static com.eindopdracht_into_art.helpers.Validator.validateAndReturnErrorsIfAny;


@RestController
@RequestMapping(EP_NEWSLETTER)
public class SubscriberController {

    private final SubscriberService service;

    public SubscriberController(SubscriberService service) {
        this.service = service;
    }


    @PostMapping()
    public ResponseEntity<Object> createSubscriberForNewsLetter(
            @Valid @RequestBody SubscriberInputDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            return validateAndReturnErrorsIfAny(br);
        }
        generateTokenAndSetToDto(dto);
        SubscriberResponse record = new SubscriberResponse(service.createSubscriber(dto));

        return ResponseEntity.created(record.getLocation()).body(record.getConfirmTokenMessage());
    }

    @PutMapping("/{id}/{confirmationToken}")
    public ResponseEntity<String> confirmEmailByTokenToConfirmSubscription(
            @PathVariable Long id,
            @PathVariable() String confirmationToken
    ) {
        final var dto = setConfirmedTokenToDto(confirmationToken);
        SubscriberResponse record = new SubscriberResponse(service.confirmSubscription(id, dto));

        return ResponseEntity.ok().body(record.getSubscriberConfirmedMessage());
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteSubscriber(
            @Valid @RequestBody SubscriberInputDto dto, BindingResult br
    ) {
        if (br.hasErrors()) {
            validateAndReturnErrorsIfAny(br);
        }
        service.deleteSubscriberByEmail(dto);

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
