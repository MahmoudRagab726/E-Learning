package com.three2one.exception;

public class StudentException extends Exception {
    public final static String INACTIVE_USER = "";

    private String statusCode;


    public StudentException(String statusCode) {
        super(statusCode);
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
