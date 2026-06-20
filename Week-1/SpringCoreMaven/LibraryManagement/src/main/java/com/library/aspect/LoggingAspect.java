package com.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // Before advice - Exercise 8
    @Before("execution(* com.library.service.*.*(..))")
    public void logBefore(org.aspectj.lang.JoinPoint joinPoint) {
        System.out.println("[LOG] Before: " + joinPoint.getSignature().getName());
    }

    // After advice - Exercise 8
    @After("execution(* com.library.service.*.*(..))")
    public void logAfter(org.aspectj.lang.JoinPoint joinPoint) {
        System.out.println("[LOG] After: " + joinPoint.getSignature().getName());
    }

    // Around advice - logs execution time - Exercise 3
    @Around("execution(* com.library.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsed = System.currentTimeMillis() - start;
        System.out.println("[LOG] " + joinPoint.getSignature().getName() + " executed in " + elapsed + "ms");
        return result;
    }
}
