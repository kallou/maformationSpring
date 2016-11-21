import org.example.greeter.Greeter;
import org.example.greeting.Greeting;
import org.example.greeting.HelloGreeting;

public class Main {
	

	public static void main(String [] args) {
		Greeter greeter = new Greeter();
		Greeting helloGreeting = new HelloGreeting();
		
		greeter.setGreeting(helloGreeting);
		System.out.println(greeter.greet(args[0]));
		
	}
}
