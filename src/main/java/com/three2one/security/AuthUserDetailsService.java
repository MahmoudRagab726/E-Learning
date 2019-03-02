package com.three2one.security;

import com.three2one.model.Student;
import com.three2one.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Student student = getStudentDetail(email);
        if (student == null) {
            throw new UsernameNotFoundException("User Doesn't Exist");
        }
        return student;
    }


    private Student getStudentDetail(String email) {

        Student student = studentRepository.findStudentByEmail(email);

        return student;
    }
}
