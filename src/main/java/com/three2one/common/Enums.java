package com.three2one.common;

public class Enums {
    public enum StudentStatus {
        INACTIVE,
        ACTIVE,
        BLOCKED
    }

    public enum StatusCodes {
        SUCCESS("000"),
        INACTIVE_STUDENT("100"),
        STUDENT_ALREADY_EXIST("101"),
        STUDENT_DOES_NOT_EXIST("108"),
        INVALID_EMAIL_FORMAT("102"),
        INVALID_PASSWORD_FORMAT("103"),
        PASSWORD_NOT_MATCH_CONFIRMED_PASSWORD("104"),
        FAILED_TO_DELETE("105"),
        COURSE_DOES_NOT_EXIST("106"),
        MISSING_DATA("107"),
        IN_CORRECT_ACTIVATION_CODE("108"),
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
