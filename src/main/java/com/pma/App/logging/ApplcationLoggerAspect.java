package com.pma.App.logging;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class ApplcationLoggerAspect {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("within(com.pma.App.controllers..*)")
	public void definePackagePointCuts() {
		//empty Method just to name the location specified in the pointcut
	}
	@Around("definePackagePointCuts()")
	public Object logAfter(ProceedingJoinPoint jp) {
		log.debug("\n \n \n \n \n \n");
		log.debug("************************ BEFORE METHOD EXECUTION ******************************* \n {}.{} () with args = {}" ,
				jp.getSignature().getDeclaringTypeName() , jp.getSignature().getName() ,
				 Arrays.toString(jp.getArgs()));
		log.debug("___________________________________________ \n \n");
		
		Object object = null;
		
		try {
			object = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("************************ AFTER METHOD EXECUTION ******************************* \n {}.{} () with args = {}" ,
				jp.getSignature().getDeclaringTypeName() , jp.getSignature().getName() ,
				 Arrays.toString(jp.getArgs()));
		log.debug("___________________________________________ \n \n");
		
		return object ;
	}
}
