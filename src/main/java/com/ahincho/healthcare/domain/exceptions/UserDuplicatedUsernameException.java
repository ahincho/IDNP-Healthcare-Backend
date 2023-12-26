package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.UserExceptionMessages.DUPLICATED_USERNAME;

public class UserDuplicatedUsernameException extends Exception {
    public UserDuplicatedUsernameException() {
        super(DUPLICATED_USERNAME);
    }
}
