package org.example.controller;

import org.example.aspect.LoggedAspectAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	@LoggedAspectAnnotation
	public Hello hello(){
		Hello hello = new Hello("hello, ", "World!");
		return hello;
	}
	
	public static class Hello{
		String greeting;
		String name;
		
		public Hello(String s1, String s2){
			greeting=s1;
			name=s2;
			
		}
		public String getGreeting() {
			return greeting;
		}
		public void setGreeting(String greeting) {
			this.greeting = greeting;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}		
	}
}
