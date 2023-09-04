package com.example.prime.number.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * AOP Class for Logging around service layer
 */
@Component
@Aspect
@Slf4j
public class LoggingAspect {
    @Before(value = "execution(* com.example.prime.number.service.*.*(..)) and args(number)")
    public void beforeAdvice(JoinPoint joinPoint, int number) {
        log.info("Before method:" + joinPoint.getSignature());
        log.info("Fetching prime numbers upto range - " + number );
    }

    @After(value = "execution(* com.example.prime.number.service.*.*(..)) and args(number)")
    public void afterAdvice(JoinPoint joinPoint, int number) {
        log.info("After method:" + joinPoint.getSignature());

        log.info("Successfully fetched prime numbers upto range - " + number );
    }
}