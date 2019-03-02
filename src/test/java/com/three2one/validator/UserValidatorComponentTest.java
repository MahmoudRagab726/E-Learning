package com.three2one.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserValidatorComponentTest {

   StudentValidatorComponent component= new StudentValidatorComponent();

    @Test
    public void whenValidEmailSyntax(){
        assertThat(component.validateEmailExpression("mragab@yahoo.com")).isEqualTo(true);
    }
    @Test
    public void whenInvalidEmailSyntax(){
        assertThat(component.validateEmailExpression("mragab@yahoo")).isEqualTo(false);
    }
    @Test
    public void whenValidPasswordSyntax(){
        assertThat(component.validatePasswordExpression("mahmoud5475458@Mahmoud")).isEqualTo(true);
    }
    @Test
    public void whenInvalidPasswordSyntax(){
        assertThat(component.validatePasswordExpression("mragab123")).isEqualTo(false);
    }
}
