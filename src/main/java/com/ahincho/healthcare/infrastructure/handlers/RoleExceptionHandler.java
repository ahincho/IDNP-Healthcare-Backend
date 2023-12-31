package com.ahincho.healthcare.infrastructure.handlers;

import com.ahincho.healthcare.domain.exceptions.RoleDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RoleExceptionHandler {
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Void> roleNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(RoleDuplicatedException.class)
    public ResponseEntity<Void> roleDuplicated() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
