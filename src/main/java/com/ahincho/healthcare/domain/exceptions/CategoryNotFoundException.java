package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.CategoryExceptionMessages.NOT_FOUND;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
        super(NOT_FOUND);
    }
}
