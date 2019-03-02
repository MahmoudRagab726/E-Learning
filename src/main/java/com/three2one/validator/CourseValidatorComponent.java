package com.three2one.validator;

import com.three2one.common.CourseInfo;
import com.three2one.exception.MissingDataException;
import org.springframework.stereotype.Component;

@Component
public class CourseValidatorComponent {

    public void validateCourseInfo(CourseInfo courseInfo) throws MissingDataException {
            if(isEmptyOrNull(courseInfo.getName())){
                throw new MissingDataException();
            }else if (isEmptyOrNull(courseInfo.getDesc())){
                throw new MissingDataException();
            }else if(courseInfo.getStudentId()==null){
                throw new MissingDataException();
            }
    }



    public boolean isEmptyOrNull(String value){
        if(value==null || value.trim().equals("")){
            return true;
        }
        return false;
    }
}
