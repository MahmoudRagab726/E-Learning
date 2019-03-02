package com.three2one.repository;

import com.three2one.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {
    //public List<Course> findAll(Long customerId);
    public Course findCourseById(Long id);

}
