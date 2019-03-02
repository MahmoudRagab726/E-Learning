package com.three2one.exception;

import com.three2one.common.Enums.*;

public class FailureOperationException extends GeneralFailureException {
    public FailureOperationException(String message) {
        super(message);
    }

    @Override
    public StatusCodes getStatusCode() {
        return StatusCodes.FAILED_TO_PERFORM;
    }
}
