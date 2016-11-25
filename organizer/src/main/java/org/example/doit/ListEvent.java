package org.example.doit;

import java.util.List;

import javax.inject.Inject;

import org.example.organizer.model.Event;

public class ListEvent implements Command{

	@Inject
	private EventDao eventDao;
	

	@Override
	public void execute(String[] args) {
		List<Event> events = eventDao.findAll();

		System.out.println("events size:"+events.size());		
		for (Event event:events){
			System.out.println("even :"+event);		
		}
		
	}
	
	

}
