package ru.kalen.taskmodule.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAspect {

    @Around("@annotation(ru.kalen.taskmodule.aspects.TrackUserAction)")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getClass().toString();
        // String args = joinPoint.getArgs().toString();
        Object [] args = joinPoint.getArgs();

        System.out.println("Пользователь вызвал метод: " +
                methodName + ", в классе: " + className);
        System.out.println("Аргументы: " + Arrays.toString(args));

        Object proceed = joinPoint.proceed();

        System.out.println("Метод отработал.");
        return proceed;
    }

}
