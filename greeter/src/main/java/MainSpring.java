import org.example.greeter.Greeter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainSpring {
	

	public static void main(String [] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		
		Greeter greeter = context.getBean(Greeter.class);
		System.out.println(greeter.greet(args[0]));
		
		
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		context.close();
		
	}
}
