package com.project.janus.framework.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.InvocationTargetException;

import com.project.janus.framework.handlers.FTLViewHandler;
import com.project.janus.framework.handlers.JavaControllerHandler;
import com.project.janus.framework.utils.UtilHttp;
import com.project.janus.framework.webapp.Controller;
import com.project.janus.framework.webapp.WebApp;

import freemarker.template.TemplateException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class WebServlet extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String context = UtilHttp.getContext(request);
		WebApp webapp = UtilHttp.getWebApp(context);
		Controller.RequestMap requestMap = webapp.getRequestMap(context);
		if(requestMap != null) {
			try {
				String resp = JavaControllerHandler.runEvent(requestMap.event, request, response);
				Controller.ResponseMap respMap = webapp.getResponseMap(resp, requestMap.response);
				
				try {
					Map<String,Object> ftlContext = new HashMap<>();
					FTLViewHandler.render(respMap.location, request, response, ftlContext);
				} catch (IOException | TemplateException e) {
					e.printStackTrace();
				}
				
			} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		System.out.print(context);
	}

}
