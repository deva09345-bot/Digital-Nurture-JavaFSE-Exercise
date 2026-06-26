package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Exercise 8: Before advice
    @Before("execution(* com.library.service.BookService.*(..))")
    public void logBefore() {
        System.out.println("LoggingAspect: Before method execution");
    }

    // Exercise 8: After advice
    @After("execution(* com.library.service.BookService.*(..))")
    public void logAfter() {
        System.out.println("LoggingAspect: After method execution");
    }

    // Exercise 3: Around advice to log execution time
    @Around("execution(* com.library.service.BookService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;
        System.out.println("LoggingAspect: Method [" + joinPoint.getSignature().getName()
                + "] executed in " + duration + " ms");
        return result;
    }
}
