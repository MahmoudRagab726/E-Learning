package com.three2one.exception;

public class MissingDataException extends Exception {
    public MissingDataException() {
    }

    public MissingDataException(String message) {
        super(message);
    }

    public MissingDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
