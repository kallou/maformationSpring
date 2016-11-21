package org.example;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;

import java.util.Arrays;

import javax.inject.Inject;

import org.example.greeter.Greeter;
import org.example.greeting.Greeting;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath*:application-context.configuration.xml"
})
public class PrototypeScopeTest {
	@Inject
	private Greeter greeterTOTO;
	
	@Inject
	private Greeter greeterTATA;
	
	@Inject
	ApplicationContext ctx;
	
	@Test
	public void test() {
		
		System.out.println(ctx);
		String[] all = ctx.getBeanDefinitionNames();
		for (String a:all){
			Object o = ctx.getBean(a);

			System.out.println("bean:"+a+", hc:"+o.hashCode()+", alias:"+Arrays.asList(ctx.getAliases(a)));
		}
		System.out.println("-------------");
		System.out.println("greeterTOTO:, hc:"+greeterTOTO.hashCode());
		System.out.println("greeterTATA:, hc:"+greeterTATA.hashCode());
		Greeting greeting1 = greeterTOTO.getGreeting();
		Greeting greeting2 = greeterTATA.getGreeting();

		Assert.assertThat(greeterTOTO, not(sameInstance(greeterTATA)));
		//Assert.assertThat(greeting1, not(sameInstance(greeting2)));
		//Assert.assertThat(greeting1,  sameInstance(greeting2));
	}
}
