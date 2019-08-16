package cs545.waa.project.sellingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = {"/", "/home", "/index"})
	public String showHomePage() {
		return "home";
	}
	
	@PostMapping(value = {"/home"})
	public String showDashboard() {
		return "home";
	}
	
	@GetMapping("/not-found")
	public String showNotFoundPage() {
		return "404";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "403";
	}
}
