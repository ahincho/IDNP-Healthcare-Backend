package com.ahincho.healthcare.domain.exceptions.users;

import static com.ahincho.healthcare.domain.exceptions.users.UserExceptionMessages.NOT_FOUND;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super(NOT_FOUND);
    }
}
