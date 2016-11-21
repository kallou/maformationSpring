package org.example;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

import javax.inject.Inject;

import org.example.greeter.Greeter;
import org.example.greeting.Greeting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		ApplicationConfiguration.class,
})
public class PrototypeScopeTest {
	@Inject
	private Greeter greeter1;
	
	@Inject
	private Greeter greeter2;
	
	@Test
	public void test() {
		Greeting greeting1 = greeter1.getGreeting();
		Greeting greeting2 = greeter2.getGreeting();

		Assert.assertThat(greeter1, not(sameInstance(greeter2)));
		Assert.assertThat(greeting1, not(sameInstance(greeting2)));
		//Assert.assertThat(greeting1,  sameInstance(greeting2));
	}
}
