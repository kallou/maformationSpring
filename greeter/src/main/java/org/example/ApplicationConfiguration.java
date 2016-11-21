package org.example;
import org.example.greeter.Greeter;
import org.example.greeting.BonjourGreeting;
import org.example.greeting.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class ApplicationConfiguration {
	/*
	@Bean
	public Greeter greeter(){
		Greeter greeter = new Greeter();
		greeter.setGreeting(greeting());
		return greeter;
	}
	
	@Bean
	public Greeting greeting(){
		Greeting greeting = new BonjourGreeting();
		return greeting;
	}
	*/
	

}
