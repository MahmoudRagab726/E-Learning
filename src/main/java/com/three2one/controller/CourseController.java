package com.three2one.controller;

import com.three2one.common.CourseInfo;
import com.three2one.common.CourseManager;
import com.three2one.common.Response;
import com.three2one.exception.MissingDataException;
import com.three2one.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/management")
public class CourseController {
    @Autowired
    CourseManager courseManager;

    @RequestMapping(value = "/course",method = RequestMethod.POST)
    public Response addCourse(@RequestBody CourseInfo courseInfo) throws MissingDataException {
        Response response = courseManager.addCourse(courseInfo);
        return response;
    }
    @RequestMapping(value = "/course",method = RequestMethod.PUT)
    public Response updateCourse(@RequestBody CourseInfo courseInfo) throws NotFoundException, MissingDataException {
        Response response = courseManager.updateCourse(courseInfo);
        return response;
    }
    @RequestMapping(value = "/course/{courseId}",method = RequestMethod.DELETE)
    public Response deleteCourse(@PathVariable Long courseId){
        Response response = courseManager.deleteCourse(courseId);
        return response;
    }
    @RequestMapping(value = "/student/{studentId}/course/{courseId}",method = RequestMethod.GET)
    public Response subscribeCourse(@PathVariable Long studentId,@PathVariable Long courseId) throws NotFoundException {
        Response response = courseManager.subscribeCourse(studentId,courseId);
        return response;
    }
    @RequestMapping(value = "/student/{studentId}",method = RequestMethod.GET)
    public Response getStudentCourses(@PathVariable Long studentId){
        Response response = courseManager.getStudentCourses(studentId);
        return response;
    }
}
