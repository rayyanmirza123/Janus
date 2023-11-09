package com.project.janus.framework.handlers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.project.janus.framework.webapp.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JavaControllerHandler {

	public static String runEvent(Controller.RequestMap.RequestEvent event,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if(event == null) {
			return "error";
		}
		
		Thread eventThread = new Thread();
		
		Class<?> clazzPath = eventThread.getContextClassLoader().loadClass(event.path);
		
		Method method = clazzPath.getDeclaredMethod(event.invoke, HttpServletRequest.class, HttpServletResponse.class);
		
		if(method == null) {
			return "error";
		}
		
		String resp = (String)method.invoke(null, request, response);
		
		return resp;
	}
	
}
