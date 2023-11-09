package com.project.janus.framework.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void render(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException {
		request.getRequestDispatcher(view).forward(request, response);
	}
	
	

}
