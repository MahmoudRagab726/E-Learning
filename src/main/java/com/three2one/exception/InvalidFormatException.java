package com.three2one.exception;

import com.three2one.common.Enums.*;

public class InvalidFormatException extends GeneralFailureException {
    public InvalidFormatException(String message) {
        super(message);
    }

    @Override
    public StatusCodes getStatusCode() {
        return StatusCodes.INVALID_FORMAT;
    }

}
