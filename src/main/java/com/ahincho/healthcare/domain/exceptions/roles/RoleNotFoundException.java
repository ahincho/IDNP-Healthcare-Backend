package com.ahincho.healthcare.domain.exceptions.roles;

import static com.ahincho.healthcare.domain.exceptions.roles.RoleExceptionMessages.NOT_FOUND;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException() {
        super(NOT_FOUND);
    }
}
