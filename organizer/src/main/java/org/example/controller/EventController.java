package org.example.controller;

import java.util.List;

import javax.inject.Inject;

import org.example.doit.EventDao;
import org.example.organizer.model.Event;
import org.example.validator.EventValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/events")
public class EventController {
	@Inject
	public EventDao eventDao;
	
	@Inject
	private EventValidator eventValidator;
		
		
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.setValidator(eventValidator);		
	}
		
	@RequestMapping(method=RequestMethod.GET)
	public String getAllEvents(Model model){
		List<Event> lEvents = eventDao.findAll();		
		model.addAttribute("listOfEvents", lEvents);
		return "events/list";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String editOrCReateEvent(
			@Validated @ModelAttribute("event") Event event,
			BindingResult result){		
		
		if (result.hasErrors()){
			return  "events/edit";
		}
		
		if (event.getId()==null){
			eventDao.save(event);
		} else {
			Event current = eventDao.findById(event.getId());
			current.setDescription(event.getDescription());
			current.setBeginDateTime(event.getBeginDateTime());
			current.setEndDateTime(event.getEndDateTime());
			eventDao.save(current);			
		}
		
		eventDao.save(event);
		return "events/edit";	
	}
	
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public String createEvent(Model model){
		model.addAttribute("event", new Event());
		return "events/edit";	
	}
	
	
	@RequestMapping("/{id}")
	public String getEvent(@PathVariable("id") Long idOfEvent, Model model){
		Event event  = eventDao.findById(idOfEvent);
		model.addAttribute("id", idOfEvent);
		model.addAttribute("event", event);
		return "events/show";
	}

	@RequestMapping("/{id}/edit")
	public String editEvent(@PathVariable("id") Long idOfEvent, Model model){
		Event event  = eventDao.findById(idOfEvent);
		model.addAttribute("event", event);
		return "events/edit";
	}
	
}
