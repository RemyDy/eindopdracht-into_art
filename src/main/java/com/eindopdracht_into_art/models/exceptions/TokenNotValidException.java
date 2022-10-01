package com.eindopdracht_into_art.models.exceptions;

public class TokenNotValidException extends RuntimeException {
    public static final String TOKEN_NOT_VALID = "Token is niet geldig";
    public TokenNotValidException() {
        super(TOKEN_NOT_VALID);
    }
    public TokenNotValidException(String message) {
        super(message);
    }
}

