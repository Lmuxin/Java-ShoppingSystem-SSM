package com.elec.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestPages {
	@RequestMapping("/")
	public String welcome() {
		
		return "index";
		
	}
	
	@RequestMapping("{page}")
	public String showPages(@PathVariable String page) {
		
		return page;
	}
	

}
