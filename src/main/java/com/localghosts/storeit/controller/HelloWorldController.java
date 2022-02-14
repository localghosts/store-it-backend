package com.localghosts.storeit.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
public class HelloWorldController {

	@RequestMapping({ "/" })
	public String hello() {
		return "Hello World";
	}

}
