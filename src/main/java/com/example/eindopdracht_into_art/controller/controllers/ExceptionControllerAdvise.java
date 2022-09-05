package com.example.eindopdracht_into_art.controller.controllers;

import com.example.eindopdracht_into_art.model.exceptions.RecordExistsException;
import com.example.eindopdracht_into_art.model.exceptions.RecordNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ExceptionControllerAdvise
        extends ResponseEntityExceptionHandler {

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
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}