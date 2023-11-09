package com.project.janus.framework.webapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;

public class WebAppLoader {
	
	private static WebAppLoader webApps;
	
	private static WebApp webappConfig;
	
	private static final Gson gson = new Gson();
	
	private WebAppLoader(String webAppConfigFilePath) {
		
	}
	
	public static WebAppLoader getInstance(String webAppConfigFilePath) throws IOException {
		if(webApps == null) {
			webApps = new WebAppLoader(webAppConfigFilePath);
		}
		loadWebApps(webAppConfigFilePath);
		return webApps;
	}
	
	private static void loadWebApps(String webAppConfigFilePath) throws IOException {
		String appPath = System.getProperty("user.dir");
		String config = new String(Files.readAllBytes(Paths.get(webAppConfigFilePath)));
		webappConfig = gson.fromJson(config, WebApp.class);
		for(WebApp webapp : webappConfig.webapps) {
			webapp.controller = Controller.loadController(appPath+"/runtime/"+webapp.webappPath+webapp.webappController);
		}
	}
	
	public static Controller loadController(String path) throws IOException {
		return Controller.loadController(path);
	}
	
	public static List<WebApp> getWebApps(){
		return webappConfig.webapps;
	}
	
	public static WebApp getWebApp(String webappName) {
		for(WebApp webapp : webappConfig.webapps) {
			if(webappName.equals(webapp.webappName)) {
				return webapp;
			}
		}
		return null;
	}

}
