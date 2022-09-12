package com.example.eindopdracht_into_art.model.exceptions;

public class TokenNotValidException extends RuntimeException {
    private static final String TOKEN_NOT_VALID = "Token is niet geldig";
    public TokenNotValidException() {
        super(TOKEN_NOT_VALID);
    }
    public TokenNotValidException(String message) {
        super(message);
    }
}

