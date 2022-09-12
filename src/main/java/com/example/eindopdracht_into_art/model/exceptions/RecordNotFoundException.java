package com.example.eindopdracht_into_art.model.exceptions;

public class RecordNotFoundException extends RuntimeException {
    private static final String DATA_NIET_GEVONDEN = "Data niet gevonden";
    public RecordNotFoundException(){
        super(DATA_NIET_GEVONDEN);
    }
    public RecordNotFoundException(String message){
        super(message);
    }
}
