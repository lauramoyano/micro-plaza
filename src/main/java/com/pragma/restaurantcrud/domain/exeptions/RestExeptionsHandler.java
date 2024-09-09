package com.pragma.restaurantcrud.domain.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@ControllerAdvice
public class RestExeptionsHandler {

    private static final String MESSAGE = "message";

    @ExceptionHandler(InvalidData.class)
    public ResponseEntity<Map<String, String>> handleInvalidDataException(
            InvalidData ignoredInvalidDataException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.INVALID_DATA.getMessage()));
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(
            NotFound ignoredNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOT_FOUND.getMessage()));
    }

    @ExceptionHandler(EmptyFields.class)
    public ResponseEntity<Map<String, String>> handleEmptyFieldsException(
            EmptyFields ignoredEmptyFieldsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.EMPTY_FIELDS.getMessage()));
    }

    @ExceptionHandler(AlreadyExist.class)
    public ResponseEntity<Map<String, String>> handleAlreadyExistException(
            AlreadyExist ignoredAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.ALREADY_EXIST.getMessage()));
    }

    @ExceptionHandler(NotBelong.class)
    public ResponseEntity<Map<String, String>> handleNotBelongException(
            NotBelong ignoredNotBelongException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.NOT_BELONG.getMessage()));
    }
}
