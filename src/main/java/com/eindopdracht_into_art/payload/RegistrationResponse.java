package com.eindopdracht_into_art.payload;

public record RegistrationResponse(String username) {

    public String getResponse() {
        return """
                Registratie is succesvol verlopen.
                %s je kan nu inloggen.""".formatted(username);
    }

}
