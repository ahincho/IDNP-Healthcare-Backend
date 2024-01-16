package com.ahincho.healthcare.domain.exceptions.users;

import static com.ahincho.healthcare.domain.exceptions.users.UserExceptionMessages.DUPLICATED_EMAIL;

public class UserDuplicatedEmailException extends Exception {
    public UserDuplicatedEmailException() {
        super(DUPLICATED_EMAIL);
    }
}
