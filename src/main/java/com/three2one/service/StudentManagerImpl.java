package com.three2one.service;


import com.three2one.common.Enums;
import com.three2one.common.Response;
import com.three2one.common.StudentInfo;
import com.three2one.common.StudentManager;
import com.three2one.component.EmailServiceImpl;
import com.three2one.exception.*;
import com.three2one.model.Student;
import com.three2one.repository.StudentRepository;
import com.three2one.security.AuthUserDetailsService;
import com.three2one.security.TokenUtil;
import com.three2one.validator.StudentValidatorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import com.three2one.common.Enums.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;
@Service
public class StudentManagerImpl implements StudentManager {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    AuthUserDetailsService authUserDetailsService;
    @Autowired
    EmailServiceImpl emailService;
    @Autowired
    StudentValidatorComponent studentValidator;

    @Override
    public Response login(StudentInfo studentInfo) throws GeneralFailureException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(studentInfo.getEmail(),studentInfo.getPassword())
        );

        Response response = new Response();
        UserDetails userDetails= authUserDetailsService.loadUserByUsername(studentInfo.getEmail());
        response.setStatusCode(StatusCodes.SUCCESS.getCode());
        response.setAccessToken(tokenUtil.generateToken(userDetails));
        Student student =studentRepository.findStudentByEmail(studentInfo.getEmail());
        if(student.getStatus()==StudentStatus.INACTIVE.ordinal()){
            throw new InactiveCustomerException("User is inactive");
        }
        return response;
    }

    @Override
    public Response signUp(StudentInfo studentInfo) throws MessagingException,GeneralFailureException {
        Response response =new Response();
        response.setStudentInfo(studentInfo);

        Student student =new Student();
        String email=studentInfo.getEmail();
        String password = studentInfo.getPassword();
        String confirmedPassword = studentInfo.getConfirmedPassword();
        String OTP = generateOTP()+"";
        if (!studentValidator.validateEmailExpression(email)){
            throw new InvalidFormatException("Invalid email format");
        }else if(!studentValidator.validatePasswordExpression(password)){
            throw new InvalidFormatException("Invalid password format");
        }else if (!isPasswordMatches(password,confirmedPassword)){
            throw new IncorrectDataException("Password not match confirmed password");
        }else {
            if (!isStudentExist(studentInfo.getEmail())) {
                student.setEmail(email);
                student.setActivationCode(OTP);
                student.setPassword(passwordEncoder.encode(password));
                student.setLastModifiedDate(new Date());
                student.setStatus(Enums.StudentStatus.INACTIVE.ordinal());
                student.setStudentUserName(studentInfo.getStudentUserName());
                student.setDOB(studentInfo.getDOB());
                student.setGender(studentInfo.getGender());
                student.setName(studentInfo.getName());
                studentRepository.save(student);
                emailService.sendEmailMessage(email, "OTP Code", "You can use this OTP to activate your account " + OTP);
            } else {
                throw new AlreadyExistException("Student already exist");
            }
        }
        studentInfo.setStudentId(student.getId());
        response.setStatusCode(Enums.StatusCodes.SUCCESS.getCode());
        return response;
    }

    @Override
    public Response activateAccount(StudentInfo studentInfo) throws GeneralFailureException {
        Response response = new Response();
        Student student = studentRepository.findStudentByEmail(studentInfo.getEmail());
        if (student!=null){
            if(studentInfo.getActivationCode().equals(student.getActivationCode())){
                student.setStatus(StudentStatus.ACTIVE.ordinal());
                student.setLastModifiedDate(new Date());
                studentRepository.save(student);
            }else {
                throw new IncorrectDataException("Wrong activation code");
            }
        }else {
            throw new NotFoundException("User not found");
        }
        response.setStatusCode(StatusCodes.SUCCESS.getCode());

        return response;
    }

    @Override
    public boolean isStudentExist(String email) {
        Student student = studentRepository.findStudentByEmail(email);
        if(student !=null)
            return true;
        else
            return false;
    }

    public long generateOTP(){
        return (long)(Math.random()*100000000);
    }

    public boolean isPasswordMatches(String password,String confirmedPassword) {
        if (password.equals(confirmedPassword))
            return true;
        else
            return false;
    }
}
