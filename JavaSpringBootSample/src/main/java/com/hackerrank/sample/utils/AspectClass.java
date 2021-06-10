package com.hackerrank.sample.utils;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.hackerrank.sample.annotation.RateLimit;
import com.hackerrank.sample.exception.RateLimitException;

@Aspect
@Component
public class AspectClass {
	
	@Autowired
	private RequestCounter counter;

	@Before("@annotation(com.hackerrank.sample.annotation.RateLimit)")
	public void validateAspect(JoinPoint joinPoint) throws Throwable {
 
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String methodName = method.getName();

		RateLimit rateLimit = method.getAnnotation(RateLimit.class);
		int duration = rateLimit.duration();
		int usage = rateLimit.usage();
		String type = rateLimit.type();

		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		
		if(!counter.increment(username, methodName, duration, usage, type)) {
			throw new RateLimitException("Please wait some time before retrying");
		}

	}

}
