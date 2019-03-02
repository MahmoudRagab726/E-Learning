package com.three2one.exception;

import com.three2one.common.Enums.*;

public class IncorrectDataException extends GeneralFailureException {
    public IncorrectDataException(String message) {
        super(message);
    }

    @Override
    public StatusCodes getStatusCode() {
        return StatusCodes.IN_CORRECT_DATA;
    }
}
