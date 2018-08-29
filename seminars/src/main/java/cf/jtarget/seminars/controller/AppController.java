/**
 * 
 */
package cf.jtarget.seminars.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dron
 *
 */
@Controller
public class AppController {

	private static final String PAGES = "backup seminar professor student";
	@RequestMapping("/")
	String home(ModelMap model) {
		model.addAttribute("title", "Seminars Management");
		return "index";
	}
	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		if (!PAGES.contains(page.toLowerCase())) {
			return "seminar";
		}
		return page;
	}
}
