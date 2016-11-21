package org.example;

import org.example.doit.Command;
import org.example.doit.CreateEvent;
import org.example.doit.JacksonJsonSerializer;
import org.example.doit.JsonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrganizerApplicationContext {

	@Bean(name="create-event")
	public Command createEvent(){
		Command command = new CreateEvent();
		return command;
	}
	
	@Bean
	public JsonSerializer myserializer(){
		return new JacksonJsonSerializer();
	}
}
