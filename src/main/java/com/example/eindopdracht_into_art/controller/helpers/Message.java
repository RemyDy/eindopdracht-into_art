package com.example.eindopdracht_into_art.controller.helpers;

public final class Message {

    //region Links & Locations
    public static final String SUBSCRIBER_CREATED_LOCATION = "/into-art/newsletter/subscribe/confirm/%s";

    public static final String CONFIRM_EMAIL_LINK = "http://localhost:8080/into-art/newsletter/%s/%s";

    public static final String UNSUBSCRIBE_CONFIRM_LOCATION = "/into-art/newsletter/unsubscribe/confirm/%s";
    //endregion

    //region CRUD messages


    public static final String SUBSCRIBE
            = "subscribe";

    public static final String UNSUBSCRIBE
            = "unsubscribe";

    public static final String SUBSCRIBER_CONFIRM_EMAIL_MESSAGE
            = """
            Bedankt %s dat jij je wil abonneren op mijn nieuwsbrief!
            Er is een e-mail naar jouw adres gestuurd.
                            
            Klik op de link om jouw e-mailadres te bevestigen.
            %s
                            
            Let op: deze link is 15 minuten geldig.
            """;

    public static final String SUBSCRIBER_CONFIRMED_SUCCESS_MESSAGE
            = "Jouw e-mail adres is succesvol bevestigd";

    public static final String UNSUBSCRIBE_CONFIRM_EMAIL_MESSAGE = """
            Jammer %s dat je uitschrijft.
            Nog even je uitschrijving bevestigen via deze onderstaande link en dan ben je uitgeschreven:
            %s
            """;

    public static final String UNSUBSCRIBED_CONFIRMED_MESSAGE
            = "Je bent uitgeschreven\n";

    //endregion

    //region Exceptions

    public static final String SUBSCRIBED_CONFIRMED_MESSAGE
            = "Dit mail adres is al ingeschreven voor de nieuwsbrief";

    public static final String SUBSCRIBED_UNCONFIRMED_MESSAGE
            = """
            Je bent al ingeschreven voor de nieuwsbrief maar je mail adres is nog niet bevestigd.
            Bij je inschrijving op de nieuwsbrief is een link naar je toegestuurd, deze is 15 minuten geldig.
            KLik op de link om je e-mail adres alsnog te bevestigen.
            Kan je de link niet vinden wacht totdat de link is verlopen om je opnieuw in te schrijven
            """;

    public static final String TOKEN_EXPIRED_MESSAGE
            = "Geldigheidsduur van de token is verlopen.\n";

    public static final String SUBSCRIBE_AGAIN_MESSAGE
            = "Schrijf je opnieuw in en bevestig je e-mail adres binnen 15 minuten om de nieuwsbrief te ontvangen.\n";

    public static final String UNSUBSCRIBE_AGAIN_MESSAGE
            = "Schrijf je opnieuw uit en bevestig de token binnen 15 minuten om je uitschrijving te bevestigen\n";
    //endregion
}
