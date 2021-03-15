package kr.or.ddit.commons.advice;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class LoggingAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Around("execution(* kr.or.ddit..service.*.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		long start = System.currentTimeMillis();
		LOGGER.info("{}.{}({}) 호출", targetName, methodName, Arrays.toString(args));
		Object returnValue = joinPoint.proceed(args);
		long end = System.currentTimeMillis();
		LOGGER.info("{}.{} 소요시간 : {}ms, 반환값 : {}", targetName, methodName, (end-start), returnValue);
		return returnValue;
	}
}















