package org.example;

import javax.inject.Inject;
import javax.inject.Named;

import org.example.greeting.Greeting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:application-context.configuration.xml"
})

@ActiveProfiles("dev")
public class ProfileTest {
	@Inject
	@Named("greeting")
	public Greeting greeting; 
	
	@Test
	public void test() {
		Assert.assertNotNull(greeting);
	}
}
