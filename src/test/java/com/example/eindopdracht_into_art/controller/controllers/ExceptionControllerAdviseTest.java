package com.example.eindopdracht_into_art.controller.controllers;

import com.example.eindopdracht_into_art.model.exceptions.PreConditionNotMetException;
import com.example.eindopdracht_into_art.model.exceptions.RecordExistsException;
import com.example.eindopdracht_into_art.model.exceptions.RecordNotFoundException;
import com.example.eindopdracht_into_art.model.exceptions.TokenNotValidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.example.eindopdracht_into_art.controller.helpers.Message.SUBSCRIBED_UNCONFIRMED_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionControllerAdviseTest {

    @Test
    @DisplayName("Test should return ResponseEntity, status: 400, body: Data already exists)")
    void test_handleRecordExistsException() {
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data bestaat al");

        final var actual
                = eca.handleRecordExistsException(new RecordExistsException());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test should return ResponseEntity, status: 404, body: Data not found)")
    void handleRecordNotFoundException() {
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data is niet gevonden");

        final var actual
                = eca.handleRecordNotFoundException(new RecordNotFoundException());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test should return ResponseEntity, status: 409, body: Token not valid)")
    void handleTokenNotValidException() {
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.CONFLICT).body("Token is niet geldig");

        final var actual
                = eca.handleTokenNotValidException(new TokenNotValidException());

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test should return ResponseEntity, status: 412, body: Explanation what client has to do)")
    void handlePreConditionNotMetException(){
        ExceptionControllerAdvise eca = new ExceptionControllerAdvise();

        final var expected
                = ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(SUBSCRIBED_UNCONFIRMED_MESSAGE);

        final var actual
                = eca.handlePreConditionNotMetException(new PreConditionNotMetException(SUBSCRIBED_UNCONFIRMED_MESSAGE));

        assertEquals(expected, actual);
    }
}