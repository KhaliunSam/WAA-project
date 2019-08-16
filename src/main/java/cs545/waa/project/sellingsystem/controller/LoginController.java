package cs545.waa.project.sellingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cs545.waa.project.sellingsystem.domain.Role;
import cs545.waa.project.sellingsystem.domain.User;
import cs545.waa.project.sellingsystem.service.RoleService;
import cs545.waa.project.sellingsystem.service.UserService;
import cs545.waa.project.sellingsystem.service.UserServiceImpl;

@Controller
public class LoginController {

	private UserService userService;
	
	private RoleService roleService;
	
	@Autowired
	public LoginController(UserService userService, RoleService roleService) {
		
		this.userService = userService;
		this.roleService = roleService;
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		
		return "login";
	}
	
	@GetMapping("/signup")
	public String showSignUpForm(@ModelAttribute User user, Model model) {
		
		List<Role> roles = roleService.findRolesExceptAdmin();
		model.addAttribute("roles", roles);
		return "signup";
	}
	
	@PostMapping("/signup")
	public String createNewUser(@Valid User user, BindingResult result, Model model) {
		
		if(user.getEmail() != null && user.getEmail().length() > 0) {
			User existing = userService.findUserByEmail(user.getEmail());
	        if (existing != null)
	            result.rejectValue("email", null, "There is already an account registered with that email");
		}		
        
		if(result.hasErrors()) {
			List<Role> roles = roleService.findRolesExceptAdmin();
			model.addAttribute("roles", roles);
			return "signup";
		}
		
		userService.createUser(user);
		
		
		return "redirect:/home";
	}
}
