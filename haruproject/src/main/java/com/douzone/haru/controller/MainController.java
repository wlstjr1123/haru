package com.douzone.haru.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000"})
@Controller
@RestController
public class MainController {
	
	private static final Logger log = LoggerFactory.getLogger(MainController.class);
	
//	@RequestMapping("")
	@GetMapping("api/main")
	public String main() {
		System.out.println("push test");
		log.error("시작로그");
		return "hello world!";
	}
}
