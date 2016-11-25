package org.example.model;

import java.time.LocalDateTime;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EventSetFieldMapper implements FieldSetMapper<Event> {

	@Override
	public Event mapFieldSet(FieldSet fieldSet) throws BindException {
		String description = fieldSet.readString("description");
		LocalDateTime begin = LocalDateTime.parse(fieldSet.readString("beginDateTime"));
		LocalDateTime end   = LocalDateTime.parse(fieldSet.readString("endDateTime"));
		Event event = new Event(description, begin, end);
		return event;
	}
	

}
