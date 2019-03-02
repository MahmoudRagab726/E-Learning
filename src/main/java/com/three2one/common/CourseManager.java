package com.three2one.common;

import com.three2one.exception.MissingDataException;
import com.three2one.exception.NotFoundException;

public interface CourseManager {
    public Response addCourse(CourseInfo courseInfo) throws MissingDataException;
    public Response updateCourse(CourseInfo courseInfo) throws NotFoundException, MissingDataException;
    public Response deleteCourse(Long courseId);
    public Response subscribeCourse(Long studentId ,Long courseId) throws NotFoundException;
    public Response getStudentCourses(Long studentId);
}
