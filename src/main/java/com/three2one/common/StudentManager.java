package com.three2one.common;

import javax.mail.MessagingException;

public interface StudentManager {
    public Response login(StudentInfo studentInfo);
    public Response signUp(StudentInfo studentInfo) throws MessagingException;
    public Response activateAccount(StudentInfo studentInfo);
    public boolean isStudentExist(String email);
}
