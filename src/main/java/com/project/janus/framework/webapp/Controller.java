package com.project.janus.framework.webapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.google.gson.Gson;

public class Controller {
	
	private static final Gson gson = new Gson();
	
	private static Controller controller;
	
	public List<RequestMap> requests;
	
	public List<ResponseMap> responses;

	
	public static class RequestMap {
		public String uri;
		public RequestEvent event;
		public List<RequestResponse> response;
		
		
		public static class RequestEvent{
			public String path;
			public String invoke;
		}
		
		public static class RequestResponse{
			public String name;
			public String view;
		}
		
	}
	
	public static class ResponseMap{
		public String name;
		public String location;
	}
	
	public static Controller loadController(String controllerLocation) throws IOException {
		String controllerFileContent = new String(Files.readAllBytes(Paths.get(controllerLocation)));
		controller = gson.fromJson(controllerFileContent, Controller.class);
		return controller;
	}
	
}
