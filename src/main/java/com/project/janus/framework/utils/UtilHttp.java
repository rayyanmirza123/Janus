package com.project.janus.framework.utils;

import com.project.janus.framework.webapp.WebApp;
import com.project.janus.framework.webapp.WebAppLoader;

import jakarta.servlet.http.HttpServletRequest;

public class UtilHttp {

	public static String getContext(HttpServletRequest request) {
		
		String uri = request.getRequestURI();
		
		String path[] = uri.split("/");
		
		if(path != null && path.length > 0) {
			return path[1];
		}
		return "main";
	}
	
	
	public static WebApp getWebApp(String webApp) {
		return WebAppLoader.getWebApp(webApp);
	}
}
