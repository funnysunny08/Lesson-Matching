package com.az.server.exception.model;

import com.az.server.exception.Error;

public class NotFoundException extends CustomException {
    public NotFoundException(Error error, String message) {
        super(error, message);
    }
}
