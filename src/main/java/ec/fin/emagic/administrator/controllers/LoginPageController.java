package ec.fin.emagic.administrator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginPageController {

	@GetMapping("/index")
	public String indexStore() {
		return "index";
	}
}
