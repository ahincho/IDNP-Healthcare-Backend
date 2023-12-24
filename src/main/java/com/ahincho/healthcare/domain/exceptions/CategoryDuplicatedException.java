package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.CategoryExceptionMessages.DUPLICATED;

public class CategoryDuplicatedException extends RuntimeException {
    public CategoryDuplicatedException() {
        super(DUPLICATED);
    }
}
