package com.eindopdracht_into_art.models.exceptions;

public class PreConditionNotMetException extends RuntimeException {

    public static final String PRECONDITIONS_NOT_MET = "Er zijn niet aan alle voorwaarden voldaan";

    public PreConditionNotMetException(){
         super(PRECONDITIONS_NOT_MET);
     }

    public PreConditionNotMetException(String message) {
        super(message);
    }
}
