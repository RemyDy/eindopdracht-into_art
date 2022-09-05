package com.example.eindopdracht_into_art.model.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Object obj) {
        super("%s not found".formatted(obj));
    }
}
