package com.example.eindopdracht_into_art.model.exceptions;

public class RecordExistsException extends RuntimeException{

    public RecordExistsException(Object obj) {
        super("%s already exists".formatted(obj));
    }
}
