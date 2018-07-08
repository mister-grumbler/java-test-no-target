/**
 * 
 */
package cf.jtarget.seminars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dron
 *
 */
@Controller
public class AppController {

	@RequestMapping("/")
	String home(ModelMap modal) {
		modal.addAttribute("title", "Seminars Management");
		return "index";
	}
}
