package com.ahincho.healthcare.infrastructure.handlers;

import com.ahincho.healthcare.domain.exceptions.categories.CategoryDuplicatedException;
import com.ahincho.healthcare.domain.exceptions.categories.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CategoryExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Void> categoryNotFound() {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(CategoryDuplicatedException.class)
    public ResponseEntity<Void> categoryDuplicated() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
