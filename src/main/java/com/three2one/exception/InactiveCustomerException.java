package com.three2one.exception;

import com.three2one.common.Enums.*;

public class InactiveCustomerException extends GeneralFailureException {

    public InactiveCustomerException(String message) {
        super(message);
    }

    @Override
    public StatusCodes getStatusCode() {
        return StatusCodes.INACTIVE;
    }
}
