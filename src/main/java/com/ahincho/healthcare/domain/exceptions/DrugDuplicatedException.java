package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.DrugExceptionMessages.DUPLICATED;

public class DrugDuplicatedException extends RuntimeException {
    public DrugDuplicatedException() {
        super(DUPLICATED);
    }
}
