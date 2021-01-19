package com.pma.App.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController   {
	
	@GetMapping("/ErrorPath")
	public String handleError(HttpServletRequest request)  {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if(status !=null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				return "Error/404";
			}
			else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "Error/500";
				
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				return "Error/403";
			}
		}
		
		return "Error/error";
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/ErrorPath";
	}
	

}
