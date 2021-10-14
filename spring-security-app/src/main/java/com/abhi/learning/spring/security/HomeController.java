package com.abhi.learning.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String all() {
		return "Welcome";
	}

	@GetMapping("/guest")
	public String guest() {
		return "Welcome Guest";
	}

	@GetMapping("/admin")
	public String admin() {
		return "Welcome Admin";
	}
}
