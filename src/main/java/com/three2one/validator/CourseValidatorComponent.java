package com.three2one.validator;

import com.three2one.common.CourseInfo;
import com.three2one.exception.MissingDataException;
import org.springframework.stereotype.Component;

@Component
public class CourseValidatorComponent {

    public void validateCourseInfo(CourseInfo courseInfo) throws MissingDataException {
            if(isEmptyOrNull(courseInfo.getName())){
                throw new MissingDataException("Name is missing");
            }else if (isEmptyOrNull(courseInfo.getDesc())){
                throw new MissingDataException("Description is missing");
            }else if(isEmptyOrNull(courseInfo.getInstructor())){
                throw new MissingDataException("Instructor is missing");
            }else if(isEmptyOrNull(courseInfo.getTotalHours())){
                throw new MissingDataException("Total hours is missing");
            }
    }



    public boolean isEmptyOrNull(String value){
        if(value==null || value.trim().equals("")){
            return true;
        }
        return false;
    }
}
