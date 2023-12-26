package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.RoleExceptionMessages.DUPLICATED;

public class RoleDuplicatedException extends Exception {
    public RoleDuplicatedException() {
        super(DUPLICATED);
    }
}
