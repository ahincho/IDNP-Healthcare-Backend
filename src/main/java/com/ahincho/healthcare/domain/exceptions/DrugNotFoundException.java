package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.DrugExceptionMessages.NOT_FOUND;

public class DrugNotFoundException extends Exception {
    public DrugNotFoundException() {
        super(NOT_FOUND);
    }
}
