package org.example.validator;

import org.example.organizer.model.Event;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EventValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Event.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Event event = (Event)target;
		
		if (event.getDescription()==null || event.getDescription().isEmpty()){
			errors.rejectValue("description", "notEmpty");
		}
	}

}
