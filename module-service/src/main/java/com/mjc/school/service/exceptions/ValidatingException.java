package com.mjc.school.service.exceptions;

public class ValidatingException extends RuntimeException {
    public ValidatingException() {
    }

    public ValidatingException(String message) {
        super(message);
    }

    public ValidatingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatingException(Throwable cause) {
        super(cause);
    }
}
