package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.UserExceptionMessages.DUPLICATED;

public class UserDuplicatedException extends Exception {
    public UserDuplicatedException() {
        super(DUPLICATED);
    }
}
