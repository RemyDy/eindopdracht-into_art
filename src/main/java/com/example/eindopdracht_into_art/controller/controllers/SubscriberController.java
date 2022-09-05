package com.example.eindopdracht_into_art.controller.controllers;

import com.example.eindopdracht_into_art.controller.services.SubscriberService;
import com.example.eindopdracht_into_art.model.dtos.SubscriberDto;
import com.example.eindopdracht_into_art.model.dtos.SubscriberDtoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;

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
    public ResponseEntity<Object> createSubscriber(@Valid @RequestBody SubscriberDtoInput dto, BindingResult br) {

        if (br.hasErrors()) {
            return validateAndReturnErrorsIfAny(br);
        }

        final var token = createRandomToken();
        dto.setConfirmationToken(token);
        dto.setTokenCreatedAt(LocalDateTime.now());
        dto.setTokenExpiredAt(LocalDateTime.now().plusMinutes(15));

        final var record = service.createSubscriberRecord(dto);
        final var location = URI.create("/into-art/subscribe/confirm/%s/%s".formatted(record.getId(), record.getConfirmationToken()));
        final var message = messageConfirmEmail(record);

        return ResponseEntity.created(location).body(message);
    }

    private String messageConfirmEmail(SubscriberDto record) {

        final var link = "http://localhost:8080/into-art/newsletter/confirm/%s".formatted(
                record.getConfirmationToken()
        );

        return """
                Bedankt %s dat jij je wil abonneren op mijn nieuwsbrief!
                Er is een e-mail naar jouw adres gestuurd.
                                
                Klik op de link om jouw e-mailadres te bevestigen.
                %s
                                
                Let op: deze link is 15 minuten geldig.""".formatted(
                record.getName(), link
        );
    }
    //endregion

    @PatchMapping("confirm/{confirmationToken}")
    private ResponseEntity<Object> confirmEmailAdressByToken(
            @PathVariable String confirmationToken
    ) {
        service.confirmSubscriptionByConfirmationToken(confirmationToken);

        return ResponseEntity.ok().body("Jouw e-mail adres is succesvol bevestigd");
    }


    //region UNSUBSCRIBE
    @PostMapping("unsubscribe")
    public ResponseEntity<String> unSubscribe() {

        return ResponseEntity.ok().build();
    }
    //endregion

    //region Internal Methods

    //endregion
}
