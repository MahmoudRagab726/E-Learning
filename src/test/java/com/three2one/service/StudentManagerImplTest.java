package com.three2one.service;

import com.three2one.common.Enums;
import com.three2one.common.Response;
import com.three2one.common.StudentInfo;
import com.three2one.common.StudentManager;
import com.three2one.component.EmailServiceImpl;
import com.three2one.model.Student;
import com.three2one.repository.StudentRepository;
import com.three2one.security.AuthUserDetailsService;
import com.three2one.security.TokenUtil;
import com.three2one.validator.StudentValidatorComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class StudentManagerImplTest {
    @MockBean
    private StudentRepository studentRepository;
    @MockBean
    PasswordEncoder passwordEncoder;
    @MockBean
    StudentValidatorComponent studentValidatorComponent;
    @Autowired
    private StudentManager studentManager;

    @MockBean
    TokenUtil tokenUtil;
    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    AuthUserDetailsService authUserDetailsService;
    @MockBean
    EmailServiceImpl emailService;


    @TestConfiguration
    static class UserManagerContextConfiguration{
        @Bean
        public StudentManager studentManager(){
            return new StudentManagerImpl();
        }
    }
    @Test
    public void signUpTestWhenTruToAddExistEmail() throws Exception{
        StudentInfo userInfo = new StudentInfo("Mahmoud Ragab","mahmoudragab760@gmail.com","mragab","Mahmoud123@5478","Mahmoud123@5478");
        given(studentRepository.findStudentByEmail(anyString())).willReturn(new Student());
        given(studentValidatorComponent.validateEmailExpression(anyString())).willReturn(true);
        given(studentValidatorComponent.validatePasswordExpression(anyString())).willReturn(true);
        Response response = studentManager.signUp(userInfo);
        assertThat(response.getStatusCode()).isEqualTo(Enums.StatusCodes.STUDENT_ALREADY_EXIST.getCode());
    }

    @Test
    public void signUpTestWhenTruToAddInvalidMailFormat() throws Exception{
        StudentInfo userInfo = new StudentInfo("Mahmoud Ragab","mahmoudragab760@gmail.com","mragab","Mahmoud123@5478","Mahmoud123@5478");
        given(studentRepository.findStudentByEmail(anyString())).willReturn(new Student());
        given(studentValidatorComponent.validateEmailExpression(anyString())).willReturn(false);
        given(studentValidatorComponent.validatePasswordExpression(anyString())).willReturn(true);
        Response response = studentManager.signUp(userInfo);
        assertThat(response.getStatusCode()).isEqualTo(Enums.StatusCodes.INVALID_EMAIL_FORMAT.getCode());
    }
    @Test
    public void signUpTestWhenTruToAddInvalidPasswordFormat() throws Exception{
        StudentInfo userInfo = new StudentInfo("Mahmoud Ragab","mahmoudragab760@gmail.com","mragab","Mahmoud123@5478","Mahmoud123@5478");
        given(studentRepository.findStudentByEmail(anyString())).willReturn(new Student());
        given(studentValidatorComponent.validateEmailExpression(anyString())).willReturn(true);
        given(studentValidatorComponent.validatePasswordExpression(anyString())).willReturn(false);
        Response response = studentManager.signUp(userInfo);
        assertThat(response.getStatusCode()).isEqualTo(Enums.StatusCodes.INVALID_PASSWORD_FORMAT.getCode());
    }
    @Test
    public void signUpTestWhenTruToAddUnmatchedPasswords() throws Exception{
        StudentInfo userInfo = new StudentInfo("Mahmoud Ragab","mahmoudragab760@gmail.com","mragab","Mahmoud123@547","Mahmoud123@5478");
        given(studentRepository.findStudentByEmail(anyString())).willReturn(null);
        given(studentValidatorComponent.validateEmailExpression(anyString())).willReturn(true);
        given(studentValidatorComponent.validatePasswordExpression(anyString())).willReturn(true);
        Response response = studentManager.signUp(userInfo);
        assertThat(response.getStatusCode()).isEqualTo(Enums.StatusCodes.PASSWORD_NOT_MATCH_CONFIRMED_PASSWORD.getCode());
    }
    @Test
    public void signUpTestWhenTryToAddSuccessData() throws Exception{
        StudentInfo userInfo = new StudentInfo("Mahmoud Ragab","mahmoudragab760@gmail.com","mragab","Mahmoud123@5478","Mahmoud123@5478");
        given(studentRepository.findStudentByEmail(anyString())).willReturn(null);
        given(studentValidatorComponent.validateEmailExpression(anyString())).willReturn(true);
        given(studentValidatorComponent.validatePasswordExpression(anyString())).willReturn(true);
        Response response = studentManager.signUp(userInfo);
        assertThat(response.getStatusCode()).isEqualTo(Enums.StatusCodes.SUCCESS.getCode());
    }

}
