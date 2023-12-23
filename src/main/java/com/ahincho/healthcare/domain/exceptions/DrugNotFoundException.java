package com.ahincho.healthcare.domain.exceptions;

public class DrugNotFoundException extends RuntimeException {
    public DrugNotFoundException(String message) {
        super(message);
    }
}
