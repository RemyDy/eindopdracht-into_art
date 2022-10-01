package com.eindopdracht_into_art.controllers;

import com.eindopdracht_into_art.controllers.exceptions.ExceptionControllerAdvise;
import com.eindopdracht_into_art.models.exceptions.PreConditionNotMetException;
import com.eindopdracht_into_art.models.exceptions.RecordExistsException;
import com.eindopdracht_into_art.models.exceptions.RecordNotFoundException;
import com.eindopdracht_into_art.models.exceptions.TokenNotValidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import static com.eindopdracht_into_art.models.exceptions.PreConditionNotMetException.PRECONDITIONS_NOT_MET;
import static com.eindopdracht_into_art.models.exceptions.RecordExistsException.RECORD_ALREADY_EXISTS;
import static com.eindopdracht_into_art.models.exceptions.RecordNotFoundException.RECORD_NOT_FOUND;
import static com.eindopdracht_into_art.models.exceptions.TokenNotValidException.TOKEN_NOT_VALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionControllerAdviseTest {

    @Test
    @DisplayName("Test should return ResponseEntity, status: 400")
    void test_handleRecordExistsException() {
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RECORD_ALREADY_EXISTS);

        final var actual
                = eca.handleRecordExistsException(new RecordExistsException());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test should return ResponseEntity, status: 403")
    void test_handleBadCredentialsException() {
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.FORBIDDEN).body("");

        final var actual
                = eca.handleBadCredentialsException(new BadCredentialsException(""));

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test should return ResponseEntity, status: 404")
    void test_handleRecordNotFoundException() {
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.NOT_FOUND).body(RECORD_NOT_FOUND);

        final var actual
                = eca.handleRecordNotFoundException(new RecordNotFoundException());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test should return ResponseEntity, status: 409")
    void test_handleTokenNotValidException() {
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.CONFLICT).body(TOKEN_NOT_VALID);

        final var actual
                = eca.handleTokenNotValidException(new TokenNotValidException());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test should return ResponseEntity, status: 412")
    void test_handlePreConditionNotMetException(){
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(PRECONDITIONS_NOT_MET);

        final var actual
                = eca.handlePreConditionNotMetException(new PreConditionNotMetException());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test should return ResponseEntity, status: 400")
    void test_handleIllegalArgumentExceptionException(){
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");

        final var actual
                = eca.handlePreConditionNotMetException(new IllegalArgumentException());

        assertEquals(expected, actual);
    }

}