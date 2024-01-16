package com.ahincho.healthcare.domain.exceptions.categories;

import static com.ahincho.healthcare.domain.exceptions.categories.CategoryExceptionMessages.NOT_FOUND;

public class CategoryNotFoundException extends Exception {
    public CategoryNotFoundException() {
        super(NOT_FOUND);
    }
}
