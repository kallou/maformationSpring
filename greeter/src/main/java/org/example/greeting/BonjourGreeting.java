package org.example.greeting;

public class BonjourGreeting implements Greeting {
	private static final String GREETING = "Bonjour";

	@Override
	public String getGreeting() {
		return GREETING;
	}

}
