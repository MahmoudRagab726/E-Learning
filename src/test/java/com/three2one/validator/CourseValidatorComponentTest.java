package com.three2one.validator;

import com.three2one.common.CourseInfo;
import com.three2one.exception.GeneralFailureException;
import com.three2one.exception.MissingDataException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CourseValidatorComponentTest {

    CourseValidatorComponent validatorComponent;

    CourseInfo courseInfo1,courseInfo2,courseInfo3,courseInfo4,courseInfo5;
    @Before
    public void setup(){
        validatorComponent = new CourseValidatorComponent();
        courseInfo1 = new CourseInfo("Mahmoud","Ali","Mahmoud","10");
        courseInfo2 = new CourseInfo("","01272029562","Mahmoud","10");
        courseInfo3 = new CourseInfo("Mahmoud","Ali","","10");
        courseInfo4 = new CourseInfo("Mahmoud","","","10");
        courseInfo5 = new CourseInfo("Mahmoud","desc","Mahmoud","");
    }
    @Test
    public void WhenValidContactInfo() throws GeneralFailureException {
        validatorComponent.validateCourseInfo(courseInfo1);
    }
    @Test(expected = MissingDataException.class)
    public void WhenInvalidContactInfoName() throws MissingDataException {
        validatorComponent.validateCourseInfo(courseInfo2);
    }
    @Test(expected = MissingDataException.class)
    public void WhenInvalidContactInfoDesc() throws MissingDataException {
        validatorComponent.validateCourseInfo(courseInfo4);
    }
    @Test(expected = MissingDataException.class)
    public void WhenInvalidContactInfoInstructor() throws MissingDataException {
        validatorComponent.validateCourseInfo(courseInfo3);
    }
    @Test(expected = MissingDataException.class)
    public void WhenInvalidContactInfoTotalHours() throws MissingDataException {
        validatorComponent.validateCourseInfo(courseInfo5);
    }
}
