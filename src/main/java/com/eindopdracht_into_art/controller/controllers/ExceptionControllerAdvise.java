package com.eindopdracht_into_art.controller.controllers;

import com.eindopdracht_into_art.model.exceptions.RecordExistsException;
import com.eindopdracht_into_art.model.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.model.exceptions.TokenNotValidException;
import com.eindopdracht_into_art.model.exceptions.PreConditionNotMetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvise extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordExistsException.class)
    public ResponseEntity<Object> handleRecordExistsException(
            Exception ex
    ) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<Object> handleRecordNotFoundException(
            Exception ex
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<Object> handleTokenNotValidException(
            Exception ex
    ) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PreConditionNotMetException.class)
    public ResponseEntity<Object> handlePreConditionNotMetException(
            Exception ex
    ) {
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(ex.getMessage());
    }
}