package com.project.janus.main.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MainController {

	
	public static String index(HttpServletRequest request, HttpServletResponse response) {
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "success";
		
	}
	
}
