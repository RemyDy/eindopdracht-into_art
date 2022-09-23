package com.eindopdracht_into_art.model.exceptions;

public class RecordExistsException extends RuntimeException {

    public static final String RECORD_ALREADY_EXISTS = "Gegevens bestaan al";

    public RecordExistsException() {
        super(RECORD_ALREADY_EXISTS);
    }

    public RecordExistsException(String message) {
        super(message);
    }

}


