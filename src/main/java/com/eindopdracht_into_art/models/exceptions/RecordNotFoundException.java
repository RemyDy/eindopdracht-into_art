package com.eindopdracht_into_art.models.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public static final String RECORD_NOT_FOUND = "Gegevens niet gevonden";

    public RecordNotFoundException(){
        super(RECORD_NOT_FOUND);
    }

    public RecordNotFoundException(String message){
        super("%s niet gevonden".formatted(message));
    }
}
