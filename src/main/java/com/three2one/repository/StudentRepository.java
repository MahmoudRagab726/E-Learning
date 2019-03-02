package com.three2one.repository;

import com.three2one.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {
    public Student findStudentByEmail(String email);
    public Student findStudentById(Long Id);
}
