package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect//Aspect를 의미 즉 aop이다.
@Component//Bean Configuration 파일에 Bean을 따로 등록하지 않아도 사용할 수 있게 해준다. -> 스프링 빈으로 등록
public class ParameterAop {

    /*
    Aspect : 흩어진 관심사를 모듈화 한 것. 주로 부가기능을 모듈화함.
    Target : Aspect를 적용하는 곳 (클래스, 메서드 .. )
    Advice : 실질적으로 어떤 일을 해야할 지에 대한 것, 실질적인 부가기능을 담은 구현체
    JointPoint : Advice가 적용될 위치, 끼어들 수 있는 지점. 메서드 진입 지점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용가능
    PointCut : JointPoint의 상세한 스펙을 정의한 것. 'A란 메서드의 진입 시점에 호출할 것'과 같이 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있음

    Aspect 실행 시점을 지정할 수 있는 어노테이션
    @Before (이전) : 어드바이스 타겟 메소드가 호출되기 전에 어드바이스 기능을 수행
    @After (이후) : 타겟 메소드의 결과에 관계없이(즉 성공, 예외 관계없이) 타겟 메소드가 완료 되면 어드바이스 기능을 수행
    @AfterReturning (정상적 반환 이후)타겟 메소드가 성공적으로 결과값을 반환 후에 어드바이스 기능을 수행
    @AfterThrowing (예외 발생 이후) : 타겟 메소드가 수행 중 예외를 던지게 되면 어드바이스 기능을 수행
    @Around (메소드 실행 전후) : 어드바이스가 타겟 메소드를 감싸서 타겟 메소드 호출전과 후에 어드바이스 기능을 수행
    */

    //controller에 있는 모든 메서드를 감시한다.
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){

    }

    //언제 실행되는지 지정해 준다.
    @Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        System.out.println("method = " + method.getName());

        Object[] args = joinPoint.getArgs();

        for (Object obj:args){
            System.out.println("joinPoint type = " + obj.getClass().getSimpleName());
            System.out.println("joinPoint value = " + obj);
        }
    }

    //실행 후 반환값
    @AfterReturning(value = "cut()", returning = "obj")
    public void afterReturn(JoinPoint joinPoint, Object obj){
        System.out.println("return >> joinPoint = " + joinPoint + ", obj = " + obj);
    }

}
