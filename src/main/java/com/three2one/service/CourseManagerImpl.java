package com.three2one.service;

import com.three2one.common.CourseInfo;
import com.three2one.common.CourseManager;
import com.three2one.common.Enums;
import com.three2one.common.Response;
import com.three2one.exception.MissingDataException;
import com.three2one.exception.NotFoundException;
import com.three2one.model.Course;
import com.three2one.model.Student;
import com.three2one.repository.CourseRepository;
import com.three2one.repository.StudentRepository;
import com.three2one.validator.CourseValidatorComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CourseManagerImpl implements CourseManager {

    @Autowired
    CourseValidatorComponent validatorComponent;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public Response addCourse(CourseInfo courseInfo) {
        Response response = new Response();
        try {
            validatorComponent.validateCourseInfo(courseInfo);
        }catch (MissingDataException e){
            response.setStatusCode(Enums.StatusCodes.MISSING_DATA.getCode());
            return response;
        }

        Course course =new Course();
        course.setName(courseInfo.getName());
        course.setDesc(courseInfo.getDesc());
        course.setInstructor(courseInfo.getInstructor());
        course.setLastUpdateDate(new Date());
        course.setPublishDate(courseInfo.getPublishDate());
        courseRepository.save(course);
        courseInfo.setId(course.getId());

        response.setStatusCode(Enums.StatusCodes.SUCCESS.getCode());
        response.setCourseInfo(courseInfo);
        return response;
    }

    @Override
    public Response updateCourse(CourseInfo courseInfo) {
        Response response = new Response();
        try{
            Course course = courseRepository.findCourseById(courseInfo.getId());
            if (course==null){
                throw new NotFoundException();
            }
            validatorComponent.validateCourseInfo(courseInfo);
        }catch (MissingDataException e){
            response.setStatusCode(Enums.StatusCodes.MISSING_DATA.getCode());
            return response;
        }catch (NotFoundException e){
            response.setStatusCode(Enums.StatusCodes.COURSE_DOES_NOT_EXIST.getCode());
            return response;
        }catch (Exception e){
            response.setStatusCode(Enums.StatusCodes.GENERAL_FAILURE.getCode());
            return response;
        }
        Course course =new Course();
        course.setId(courseInfo.getId());
        course.setName(courseInfo.getName());
        course.setDesc(courseInfo.getDesc());
        course.setInstructor(courseInfo.getInstructor());
        course.setLastUpdateDate(new Date());
        course.setPublishDate(courseInfo.getPublishDate());
        courseRepository.save(course);
        response.setStatusCode(Enums.StatusCodes.SUCCESS.getCode());
        response.setCourseInfo(courseInfo);
        return response;
    }

    @Override
    public Response deleteCourse(Long courseId) {
        Response response = new Response();
        try {
            courseRepository.deleteById(courseId);
        }catch(NullPointerException e){
            response.setStatusCode(Enums.StatusCodes.FAILED_TO_DELETE.getCode());
            return response;
        }
        response.setStatusCode(Enums.StatusCodes.SUCCESS.getCode());
        return response;
    }

    @Override
    public Response subscribeCourse(Long studentId ,Long courseId) {
        Response response = new Response();
        Student student = studentRepository.findStudentById(studentId);
        Course course =courseRepository.findCourseById(courseId);
        if(student!=null&&course!=null){
            Set<Course> courses = student.getCourses();
            courses.add(course);
            student.setCourses(courses);
            studentRepository.save(student);
        }else {
            response.setStatusCode(Enums.StatusCodes.MISSING_DATA.getCode());
            return response;
        }
        response.setStatusCode(Enums.StatusCodes.SUCCESS.getCode());
        return response;

    }

    @Override
    public Response getStudentCourses(Long studentId) {
        Response response =new Response();
        Student student = studentRepository.findStudentById(studentId);
        List<CourseInfo> courseInfoList=new ArrayList<CourseInfo>();
        if(student!=null){
            Set<Course> courses = student.getCourses();
            if(courses!=null){
                for (Course course : courses){
                    CourseInfo courseInfo = new CourseInfo();
                    courseInfo.setId(course.getId());
                    courseInfo.setDesc(course.getDesc());
                    courseInfo.setPublishDate(course.getPublishDate());
                    courseInfo.setInstructor(course.getInstructor());
                    courseInfo.setTotalHours(course.getTotalHours());
                    courseInfoList.add(courseInfo);
                }
            }
        }
        response.setStatusCode(Enums.StatusCodes.SUCCESS.getCode());
        response.setCourses(courseInfoList);
        return response;
    }


}
