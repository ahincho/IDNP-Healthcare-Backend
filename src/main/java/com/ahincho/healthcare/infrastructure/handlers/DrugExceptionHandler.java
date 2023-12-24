package com.ahincho.healthcare.infrastructure.handlers;

import com.ahincho.healthcare.domain.exceptions.DrugDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.DrugNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DrugExceptionHandler {
    @ExceptionHandler(DrugNotFoundException.class)
    public ResponseEntity<Void> drugNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(DrugDuplicatedException.class)
    public ResponseEntity<Void> drugDuplicated() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
