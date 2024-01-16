package com.ahincho.healthcare.domain.exceptions.medicines;

import static com.ahincho.healthcare.domain.exceptions.medicines.MedicineExceptionMessages.DUPLICATED;

public class MedicineDuplicatedException extends Exception {
    public MedicineDuplicatedException() {
        super(DUPLICATED);
    }
}
