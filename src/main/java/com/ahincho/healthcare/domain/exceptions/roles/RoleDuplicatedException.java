package com.ahincho.healthcare.domain.exceptions.roles;

import static com.ahincho.healthcare.domain.exceptions.roles.RoleExceptionMessages.DUPLICATED;

public class RoleDuplicatedException extends Exception {
    public RoleDuplicatedException() {
        super(DUPLICATED);
    }
}
