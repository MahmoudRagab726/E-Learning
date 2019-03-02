package com.three2one.common;

public class Enums {
    public enum StudentStatus {
        INACTIVE,
        ACTIVE,
        BLOCKED
    }

    public enum StatusCodes {
        SUCCESS("00"),
        INACTIVE("100"),
        ALREADY_EXIST("101"),
        DOES_NOT_EXIST("108"),
        INVALID_FORMAT("102"),
        FAILED_TO_PERFORM("105"),
        MISSING_DATA("107"),
        IN_CORRECT_DATA("108"),
        GENERAL_FAILURE("99");


        private String code;

        StatusCodes(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

    }
}
