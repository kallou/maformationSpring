import org.example.ApplicationConfiguration;
import org.example.greeter.Greeter;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainSpringAnnoted {
	
	
	public static void main(String [] args) {
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		context.scan("org.example");
		
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
