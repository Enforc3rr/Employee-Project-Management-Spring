package com.pma.App.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.pma.App.dao.UserRepo;
import com.pma.App.entities.UserAccount;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model , UserAccount userAccount) {
		userAccount.setPassword(bCryptPasswordEncoder.encode(userAccount.getPassword()));
		userRepo.save(userAccount);
		return "redirect:/";
	}
}
