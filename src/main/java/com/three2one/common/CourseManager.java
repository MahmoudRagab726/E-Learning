package com.three2one.common;

import com.three2one.exception.NotFoundException;

public interface CourseManager {
    public Response addCourse(CourseInfo courseInfo);
    public Response updateCourse(CourseInfo courseInfo);
    public Response deleteCourse(Long courseId);
    public Response subscribeCourse(Long studentId ,Long courseId);
    public Response getStudentCourses(Long studentId);
}
