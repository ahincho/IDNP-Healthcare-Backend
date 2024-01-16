package com.ahincho.healthcare.domain.exceptions.categories;

import static com.ahincho.healthcare.domain.exceptions.categories.CategoryExceptionMessages.DUPLICATED;

public class CategoryDuplicatedException extends RuntimeException {
    public CategoryDuplicatedException() {
        super(DUPLICATED);
    }
}
