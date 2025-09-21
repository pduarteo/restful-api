package com.pduarteo.restful.users.support.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(UUID id) {
        super("User " + String.valueOf(id) + " not found");
    }
}
