package com.eindopdracht_into_art.model.exceptions;

public class PreConditionNotMetException extends RuntimeException {

    public static final String PRECONDITIONS_NOT_MET = "Niet alle voorwaarden zijn voldaan";

    public PreConditionNotMetException(){
         super(PRECONDITIONS_NOT_MET);
     }

    public PreConditionNotMetException(String message) {
        super(message);
    }
}
