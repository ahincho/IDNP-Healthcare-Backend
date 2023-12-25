package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.RoleExceptionMessages.NOT_FOUND;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException() {
        super(NOT_FOUND);
    }
}
