package org.example.doit;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.example.organizer.model.Event;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class StoreEvent implements Store<Event>{

	public ClassPathResource jsonfile;
	private ObjectMapper objectMapper;
	
	public public StoreEvent() {
		jsonfile =  new ClassPathResource("event.json");
		initObjectMapper();
	}
	
	private void initObjectMapper(){
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);		
	}
	
	
	@Override
	public void store(Event[] tab) {
		try (
		InputStream input = new ClassPathResource("event.json").getInputStream()) {
			Event[] event = objectMapper.readValue(input, Event[].class);
			
			assertEquals("Dentist", event[0].getDescription());
			assertEquals(LocalDateTime.of(2016, 1, 1, 15, 0), event[0].getBeginDateTime());
			assertEquals(LocalDateTime.of(2016, 1, 1, 16, 0), event[0].getEndDateTime());
		}
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public Event[] readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
