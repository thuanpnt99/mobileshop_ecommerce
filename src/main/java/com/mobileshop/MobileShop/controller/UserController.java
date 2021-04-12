package com.mobileshop.MobileShop.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mobileshop.MobileShop.entity.Role;
import com.mobileshop.MobileShop.entity.User;
import com.mobileshop.MobileShop.service.RoleService;
import com.mobileshop.MobileShop.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/signup")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user) {
		user.setStatus(true);
		user.setPassword(encoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleService.getRole("USER"));
		user.setRoles(roles);
		userService.save(user);
		return "redirect:/login";
	}
	
	@RequestMapping("/success")
	public String loginPageRedirect(Authentication authentication) {
		String role = authentication.getAuthorities().toString();
		if(role.contains("ADMIN")){
	         return "redirect:/admin";                           
	         }
	         else {
	             return "redirect:/";
	         }
	}
	
	@RequestMapping("/admin")
	public String showAdminPage() {
		return "admin";
	}
}
