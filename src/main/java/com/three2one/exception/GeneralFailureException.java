package com.three2one.exception;

import com.three2one.common.Enums.*;

public abstract class GeneralFailureException extends Exception {

    public GeneralFailureException(String message) {
        super(message);
    }
    public abstract StatusCodes getStatusCode();
}
