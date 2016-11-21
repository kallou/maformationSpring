package org.example;

import static org.junit.Assert.assertEquals;

import org.example.greeter.Greeter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={
		ApplicationConfiguration.class
})
public class ApplicationContextTest {

	@Autowired
	private Greeter greeter;
	
	@Test
	public void test(){
		String result = greeter.greet("World");
		
		// verification
		assertEquals("hello,World!", "hello,World!");
		
	}
	
}
