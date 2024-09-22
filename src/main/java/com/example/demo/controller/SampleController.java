package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	@GetMapping("/member/list")
	public String mem() {
		return "member/list";
	}
	@GetMapping("/sample/all")
	public String sample() {
		return "sample/all";
	}
	@GetMapping("/admin/main")
	public String admin() {
		return "admin/main";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
}





