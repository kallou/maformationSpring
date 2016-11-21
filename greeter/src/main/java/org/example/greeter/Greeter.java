package org.example.greeter;

import org.example.greeting.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
public class Greeter {
	
	@Autowired
	private Greeting greeting;
	
	public Greeting getGreeting() {
		return greeting;
	}
	
	public void setGreeting(Greeting greeting) {
		this.greeting = greeting;
	}
	
	public String greet(String name) {
		return String.format("%s, %s!", greeting.getGreeting(), name);
	}
}
