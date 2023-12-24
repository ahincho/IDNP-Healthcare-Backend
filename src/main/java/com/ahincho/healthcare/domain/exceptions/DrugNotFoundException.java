package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.DrugExceptionMessages.NOT_FOUND;

public class DrugNotFoundException extends RuntimeException {
    public DrugNotFoundException(String message) {
        super(NOT_FOUND);
    }
}
