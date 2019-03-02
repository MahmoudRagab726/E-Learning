package com.three2one.exception;

import com.three2one.common.Enums.*;

public class MissingDataException extends GeneralFailureException {

    public MissingDataException(String message) {
        super(message);
    }

    @Override
    public StatusCodes getStatusCode() {
        return StatusCodes.MISSING_DATA;
    }


}
