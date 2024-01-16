package com.ahincho.healthcare.infrastructure.handlers;

import com.ahincho.healthcare.domain.exceptions.medicines.MedicineDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.medicines.MedicineNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MedicineExceptionHandler {
    @ExceptionHandler(MedicineNotFoundException.class)
    public ResponseEntity<Void> drugNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MedicineDuplicatedException.class)
    public ResponseEntity<Void> drugDuplicated() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
