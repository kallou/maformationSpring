package org.example;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.example.doit.Command;
import org.example.doit.EventDao;
import org.example.organizer.model.Event;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrganizerApplicationContext.class)
public class OrganizerContextTest {
	
	@Inject
	private DataSource datasource;
	
	@Inject
	public EventDao eventDao;
	
	@Inject
	@Named("create-event")
	private Command createEvent;
		
	@Test
	@Transactional
	@Commit
	public void test() {
		Assert.assertNotNull(datasource);
		Assert.assertNotNull(createEvent);
				
		createEvent.execute(new String[]{"", "description22112016_1004", "2016-01-01T15:00:00", "2016-01-01T16:00:00"});
		
		
		Event event  = new Event( "description22112016_1004",  LocalDateTime.now());
		eventDao.save(event);
		Assert.assertNotNull(event.getId());
		
		Event event2 = eventDao.findById(event.getId());
		Assert.assertNotNull(event2);
		Assert.assertEquals(event.getId(), event2.getId());
		
		List<Event> lEvents = eventDao.findAll();
		Assert.assertNotNull(lEvents);
		
		
		
	}
	
	
	

}
