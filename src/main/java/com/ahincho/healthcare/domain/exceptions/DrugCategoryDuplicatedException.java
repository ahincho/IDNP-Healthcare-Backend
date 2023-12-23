package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.DrugCategoryExceptionMessages.DUPLICATED;

public class DrugCategoryDuplicatedException extends RuntimeException {
    public DrugCategoryDuplicatedException() {
        super(DUPLICATED);
    }
}
