package com.ahincho.healthcare.domain.exceptions;

import static com.ahincho.healthcare.domain.exceptions.UserExceptionMessages.DUPLICATED_EMAIL;

public class UserDuplicatedEmailException extends Exception {
    public UserDuplicatedEmailException() {
        super(DUPLICATED_EMAIL);
    }
}
