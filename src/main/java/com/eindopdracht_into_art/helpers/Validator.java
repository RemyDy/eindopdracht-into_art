package com.eindopdracht_into_art.helpers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.stream.Collectors;

public final class Validator {
    public static ResponseEntity<Object> validateAndReturnErrorsIfAny(BindingResult br) {
        final var errors = br.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);
    }

}