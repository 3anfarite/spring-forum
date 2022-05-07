package org.example.again.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ExceptionController implements ErrorController{
	@PostMapping("/error")
	public String errorshow() {
		return "error";
	}
	@GetMapping("/error")
	public String errorgetter() {
		return "error";
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
