package com.ahincho.healthcare.infrastructure.handlers;

import com.ahincho.healthcare.domain.exceptions.UserDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Void> userNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(UserDuplicatedException.class)
    public ResponseEntity<Void> userDuplicated() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
