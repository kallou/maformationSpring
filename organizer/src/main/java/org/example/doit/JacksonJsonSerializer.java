package org.example.doit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class JacksonJsonSerializer implements JsonSerializer {

	private ObjectMapper objectMapper;
	
	public JacksonJsonSerializer(){
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	}
	
	@Override
	public String serialize(Object o) {
		String result = null;
		try {
			result = objectMapper.writeValueAsString(o);
		} catch (Exception ex){
			throw new RuntimeException(ex);
		}
		return result;
		
	}

}
