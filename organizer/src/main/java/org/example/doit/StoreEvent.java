package org.example.doit;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.example.organizer.model.Event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class StoreEvent implements Store<Event>{

	public File jsonfile;
	private ObjectMapper objectMapper;
	
	public  StoreEvent() {
		try {
			
			String path = FileUtils.getTempDirectoryPath() +"/events.json";		
			jsonfile =  new File(path);
			if (jsonfile.exists()==false){
				jsonfile.createNewFile();
			}			
			initObjectMapper();	
		} catch (Exception ex){
			throw new RuntimeException(ex);
		}

	}
	
	private void initObjectMapper(){
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);		
	}
	
	
	@Override
	public void store(Event event) {
		try {

			Event[] events = findAll();
			List<Event> lEvents = new ArrayList<Event>(Arrays.asList(events));
			lEvents.add(event);
			
			events = lEvents.toArray(new Event[0]);
			
			String sEvents  = objectMapper.writeValueAsString(events);
			
			FileUtils.writeStringToFile(jsonfile, sEvents);		
		} catch (Exception ex){
			throw new RuntimeException(ex);
		}
		
	}

	@Override
	public Event[] findAll() {
		Event[] events = new Event[0];
		try {
			String s = FileUtils.readFileToString(jsonfile);
			if (s!=null && s.length()!=0){
				events = objectMapper.readValue(s, Event[].class);
			}
			
		} catch (Exception ex){
			throw new RuntimeException(ex);
		}
		
		return events;
	}

}
