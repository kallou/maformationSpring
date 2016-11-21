package org.example;


import org.example.greeter.Greeter;
import org.example.greeting.HelloGreeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {
	@Bean
	@Scope("prototype")
	public Greeter greeter() {
		Greeter greeter = new Greeter();
		greeter.setGreeting(greeting());
		return greeter;
	}
	

	@Bean
	@Scope("prototype")
	public HelloGreeting greeting() {
		return new HelloGreeting();
	}
}
