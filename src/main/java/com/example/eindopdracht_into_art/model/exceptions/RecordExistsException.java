package com.example.eindopdracht_into_art.model.exceptions;

public class RecordExistsException extends RuntimeException{
    public static final String DATA_BESTAAT_AL = "Data bestaat al";

    public RecordExistsException(){
        super(DATA_BESTAAT_AL);
    }
    public RecordExistsException(String message) {
        super(message);
    }
}


