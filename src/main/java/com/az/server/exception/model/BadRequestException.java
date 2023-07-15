package com.az.server.exception.model;

import com.az.server.exception.Error;

public class BadRequestException extends CustomException {
    public BadRequestException(Error error, String message) {
        super(error, message);
    }
}

