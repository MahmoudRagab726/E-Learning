package com.three2one.exception;

import com.three2one.common.Enums.*;

public class AlreadyExistException extends GeneralFailureException {
    public AlreadyExistException(String message) {
        super(message);
    }

    @Override
    public StatusCodes getStatusCode() {
        return StatusCodes.ALREADY_EXIST;
    }
}
