package com.three2one.validator;

import com.three2one.common.CourseInfo;
import com.three2one.common.StudentInfo;
import com.three2one.exception.MissingDataException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CourseValidatorComponentTest {

    CourseValidatorComponent validatorComponent;

    CourseInfo courseInfo1,courseInfo2,courseInfo3,courseInfo4;
    @Before
    public void setup(){
        validatorComponent = new CourseValidatorComponent();
        courseInfo1 = new CourseInfo("Mahmoud","Ali",55L);
        courseInfo2 = new CourseInfo("","01272029562",55L);
        courseInfo3 = new CourseInfo("Mahmoud","Ali",null);
        courseInfo4 = new CourseInfo("Mahmoud","",55L);
    }
    @Test
    public void WhenValidContactInfo() throws MissingDataException {
        validatorComponent.validateCourseInfo(courseInfo1);
    }
    @Test(expected = MissingDataException.class)
    public void WhenInvalidContactInfoName() throws MissingDataException {
        validatorComponent.validateCourseInfo(courseInfo2);
    }
    @Test(expected = MissingDataException.class)
    public void WhenInvalidContactInfoMobileNo() throws MissingDataException {
        validatorComponent.validateCourseInfo(courseInfo4);
    }
    @Test(expected = MissingDataException.class)
    public void WhenInvalidContactInfoCustomerId() throws MissingDataException {
        validatorComponent.validateCourseInfo(courseInfo3);
    }
}
