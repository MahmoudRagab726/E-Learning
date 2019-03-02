package com.three2one.common;

import com.three2one.exception.*;

import javax.mail.MessagingException;

public interface StudentManager {
    public Response login(StudentInfo studentInfo) throws InactiveCustomerException, GeneralFailureException;
    public Response signUp(StudentInfo studentInfo) throws MessagingException, InvalidFormatException, IncorrectDataException, AlreadyExistException, GeneralFailureException;
    public Response activateAccount(StudentInfo studentInfo) throws IncorrectDataException, NotFoundException, GeneralFailureException;
    public boolean isStudentExist(String email);
}
