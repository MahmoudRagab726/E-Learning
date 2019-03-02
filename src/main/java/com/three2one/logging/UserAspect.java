package com.three2one.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class UserAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Before("execution(* com.three2one.service.*.*(..))")
    public void before(JoinPoint joinPoint){
        logger.info("This Method Called {}",joinPoint.getSignature());
        Object [] arr = joinPoint.getArgs();
        for (Object obj : arr){
            logger.debug("this is passed Data to the method: \" {} \" ",obj.toString());
        }
    }

    @AfterThrowing(value = "execution(* com.three2one.service.*.*(..))",throwing = "e")
    public void exceptionsLogging(Throwable e){
        logger.error("this exception because of {}",e.getMessage());
    }

}
