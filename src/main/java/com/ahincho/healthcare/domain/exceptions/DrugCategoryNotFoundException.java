package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.DrugCategoryExceptionMessages.NOT_FOUND;

public class DrugCategoryNotFoundException extends Exception {
    public DrugCategoryNotFoundException() {
        super(NOT_FOUND);
    }
}
