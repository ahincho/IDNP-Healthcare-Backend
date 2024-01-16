package com.ahincho.healthcare.domain.exceptions.users;

import static com.ahincho.healthcare.domain.exceptions.users.UserExceptionMessages.DUPLICATED_USERNAME;

public class UserDuplicatedUsernameException extends Exception {
    public UserDuplicatedUsernameException() {
        super(DUPLICATED_USERNAME);
    }
}
