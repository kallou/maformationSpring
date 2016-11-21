package org.example;

import org.example.doit.Command;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(OrganizerApplicationContext.class);
		
		Command command = context.getBean(args[0], Command.class);
		command.execute(args);
	}
}
