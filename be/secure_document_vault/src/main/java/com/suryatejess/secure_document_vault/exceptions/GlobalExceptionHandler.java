package com.suryatejess.secure_document_vault.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {WrongUserCredentials.class})
    public ResponseEntity<Object> handleException(WrongUserCredentials e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO( e.getMessage(), HttpStatus.UNAUTHORIZED, LocalDateTime.now() );
        System.out.println("WrongUserCredentialsException: " + e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {UsernameAlreadyExistsException.class})
    public ResponseEntity<Object> handleException(UsernameAlreadyExistsException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO( e.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now() );
        System.out.println("UsernameAlreadyExistsException: " + e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.CONFLICT);
    }
}