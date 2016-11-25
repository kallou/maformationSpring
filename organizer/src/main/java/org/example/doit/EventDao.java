package org.example.doit;

import java.util.List;

import org.example.organizer.model.Event;

public interface EventDao {
		List<Event> findAll();
		
		Event findById(Long id);
		
		void saveAll(List<Event> lEvents);
		
		void save(Event event);
		
		void delete(Event event);
}
