package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.models.exceptions.RecordExistsException;
import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.models.exceptions.TokenNotValidException;
import com.eindopdracht_into_art.models.exceptions.PreConditionNotMetException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvise extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordExistsException.class)
    public ResponseEntity<Object> handleRecordExistsException(
            Exception ex
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
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

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(
            Exception ex
    ){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentExceptionException(
            Exception ex
    ){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}