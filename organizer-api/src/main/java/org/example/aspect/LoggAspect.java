package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggAspect {
		
			
	@Before("@annotation(org.example.aspect.LoggedAspectAnnotation)")
	public void logit(JoinPoint jp){
		Logger log =  LoggerFactory.getLogger(jp.getThis().getClass());
		log.info("o=====> login" );	
		Object[] args = jp.getArgs();
		for (int i=0; i<args.length; i++){
			Object toto = args[i];
			log.info("o=====>"+(toto==null ? "o is null" : toto.toString()) );			
		}		
	}
}
