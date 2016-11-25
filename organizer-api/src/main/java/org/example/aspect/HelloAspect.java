package org.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {

	public static final Logger LOGGER = LoggerFactory.getLogger(HelloAspect.class);
			
	/*		
	@Before("execution(* org.example.controller.HelloController.hello())")
	public void beforeHello(JoinPoint joinPoint){
		System.out.println("Hello was run");
	}*/
}
