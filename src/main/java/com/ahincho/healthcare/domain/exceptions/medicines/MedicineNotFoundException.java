package com.ahincho.healthcare.domain.exceptions.medicines;

import static com.ahincho.healthcare.domain.exceptions.medicines.MedicineExceptionMessages.NOT_FOUND;

public class MedicineNotFoundException extends Exception {
    public MedicineNotFoundException() {
        super(NOT_FOUND);
    }
}
