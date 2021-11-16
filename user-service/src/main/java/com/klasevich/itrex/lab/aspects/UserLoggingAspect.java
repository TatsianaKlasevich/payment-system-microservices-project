package com.klasevich.itrex.lab.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@Aspect
public class UserLoggingAspect {

    @AfterReturning("execution(* com.klasevich.itrex.lab.service.impl.UserServiceImpl.get*(*))")
    public void afterReturningGetMethodAdvice(JoinPoint joinPoint) {
        log.info(">>>>>>>>>>>>>>> Method {} has been invoked successfully", joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.klasevich.itrex.lab.service.impl.UserServiceImpl.*(*))"
            , throwing = "exception")
    public void afterThrowingUserServiceLoggingAdvice(JoinPoint joinPoint, Throwable exception) {
        log.error(">>>>>>>>>>>>>>> method {} has caught exception {}", joinPoint.getSignature().getName(), exception);
        log.error(">>>>>>>> print stack trace {}", Arrays.toString(exception.getStackTrace()));
    }
}
