package com.eindopdracht_into_art.payload;

import com.eindopdracht_into_art.models.dtos.SubscriberDto;

import java.net.URI;

import static com.eindopdracht_into_art.controllers.endpoints.ControllerEndpointConstants.EP_NEWSLETTER;
import static com.eindopdracht_into_art.helpers.UriCreator.createUriById;

public record SubscriberResponse(SubscriberDto subscriber) {

    public URI getLocation() {
        return createUriById(EP_NEWSLETTER, subscriber.getId());
    }

    public String getConfirmTokenMessage() {
        final var location = getLocation();
        final var link = "%s/%s".formatted(location, subscriber.getConfirmationToken());
        return """
                Bedankt %s dat jij je wil abonneren op mijn nieuwsbrief!
                Er is een e-mail naar jouw adres gestuurd.
                                
                Klik op de link om jouw e-mailadres te bevestigen.
                %s
                                
                Let op: deze link is 15 minuten geldig.
                
                @Teacher -> gebruik de put method anders wordt de token niet gevalideerd."""
                .formatted(subscriber.getName(), link);
    }

    public String getSubscriberConfirmedMessage() {
        return "Jouw e-mail adres is succesvol bevestigd";
    }

}
