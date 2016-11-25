package org.example.controller;


import org.example.aspect.LoggedAspectAnnotation;
import org.example.model.Event;
import org.example.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class EventRestController {
	
	@Autowired
	private EventRepository eventRepository;
	
	
	@GetMapping
	@LoggedAspectAnnotation
	public Iterable<Event> getAllEvents(){
		 Iterable<Event> itOfEvents = eventRepository.findAll();
		 return itOfEvents;		
	}


	@GetMapping("/{id}")
	public Event getEvent(@PathVariable Long id){
		Event event = eventRepository.findOne(id);
		return event;			
	}
	
	@PutMapping("/{id}")
	public Event updateEvent(@PathVariable Long id, @RequestBody Event event){
		Event current = eventRepository.findOne(id);
		current.setDescription(event.getDescription());
		current.setBeginDateTime(event.getBeginDateTime());
		current.setEndDateTime(event.getEndDateTime());
		
		eventRepository.save(current);
		return current;			
	}
	
	@DeleteMapping("/{id}")
	public Event deleteEvent(@PathVariable Long id){
		Event event = eventRepository.findOne(id);
		eventRepository.delete(event);
		return event;			
	}
	

	@PostMapping
	public Event createEvent(@RequestBody Event event){		
		eventRepository.save(event);
		return event;			
	}
	
}
