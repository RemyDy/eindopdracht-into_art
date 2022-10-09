package com.eindopdracht_into_art.payload;

public record AuthenticationResponse(String jwt) {

    public String getJwtMessage() {
        return """
                Zie onderstaand de jwt token.
                Stuur deze bij elke request mee zodat je request gehonoreerd kan worden.
                                
                Let op: deze is voor een beperkte tijd geldig.
                Is de token verlopen dient de gebruiker zich opnieuw in te loggen.
                
                @Teacher, let op gebruik de PUT method in Postman ;-)
                                
                %s""".formatted(jwt);
    }
}
