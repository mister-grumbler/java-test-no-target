/**
 * 
 */
package cf.jtarget.seminars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dron
 *
 */
@SpringBootApplication
public class SeminarsApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.class.path"));
		SpringApplication.run(SeminarsApp.class, args);

	}

}
