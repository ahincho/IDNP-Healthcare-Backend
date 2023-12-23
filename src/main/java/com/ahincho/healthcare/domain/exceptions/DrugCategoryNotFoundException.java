package com.ahincho.healthcare.domain.exceptions;

public class DrugCategoryNotFoundException extends Exception {
    public DrugCategoryNotFoundException(String message) {
        super(message);
    }
}
