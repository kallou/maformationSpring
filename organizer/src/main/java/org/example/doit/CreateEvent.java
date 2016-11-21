package org.example.doit;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.example.organizer.model.Event;

public class CreateEvent implements Command{

	@Inject
	private JsonSerializer myserializer; 
	
	public CreateEvent(){
		myserializer = new JacksonJsonSerializer();
	}
	
	@Override
	public void execute(String[] args) {
		if (args.length!=4){
			throw new RuntimeException("Impossible de creer evenement manque arguments");
		}
		int i=1;
		String desc = args[i++];
		LocalDateTime start = LocalDateTime.parse(args[i++]);
		LocalDateTime end   = LocalDateTime.parse(args[i++]);
		
		Event event  = new Event(desc,  start, end);
		System.out.println("creation event:"+event);
		
		String json = myserializer.serialize(event);

		System.out.println("json:"+json);		
		
	}
	
	

}
