package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.UserExceptionMessages.NOT_FOUND;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super(NOT_FOUND);
    }
}
