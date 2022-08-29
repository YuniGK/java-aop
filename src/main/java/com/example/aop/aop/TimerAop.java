package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
//@Bean - 메소드에서 붙일 수 있다.
//@Component - 클래스에서 붙일 수 있다.
@Component
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){

    }

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer(){}

    //시간을 측정하기 위해, 메소드 실행 전후
        //cut 그리고 enableTimer조건을 만족시켜야 한다.
    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        //메서드 실행 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

                        //실제적인 메서드가 실행된다.
        Object result = joinPoint.proceed();

        //메서드 실행 후
        stopWatch.stop();

        System.out.println("total time = " + stopWatch.getTotalTimeSeconds());

    }

}
