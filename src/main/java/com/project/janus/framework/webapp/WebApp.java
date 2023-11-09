package com.project.janus.framework.webapp;

import java.util.List;

public class WebApp {

	public String webappName;
	public String webappPath;
	public String webappContext;
	public String webappController;
	public Controller controller;
	
	public List<WebApp> webapps;
	
	public Controller.RequestMap getDefaultRequest(){
		return getRequestMap("");
	}
	
	public Controller.RequestMap getRequestMap(String uri){
		Controller.RequestMap reqestmap = null;
		for(Controller.RequestMap reqMap : controller.requests) {
			if(uri.equals(reqMap.uri)) {
				reqestmap = reqMap;
			}
		}
		return reqestmap;
	}
	
	public Controller.ResponseMap getResponseMap(String viewName, List<Controller.RequestMap.RequestResponse> responses){
		Controller.ResponseMap responseMap = null;
		
		for(Controller.RequestMap.RequestResponse respMap : responses) {
			if(viewName.equals(respMap.name)) {
				viewName = respMap.view;
				break;
			}
		}
		
		for(Controller.ResponseMap respMap : controller.responses) {
			if(viewName.equals(respMap.name)) {
				responseMap = respMap;
			}
		}
		return responseMap;
	}
	
	
	
	@Override
	public String toString() {
		return "{ \n"+
					"webappName : "+webappName+" , \n"+
					"webappPath : "+webappPath+" , \n"+
					"webappContext : "+webappContext+" , \n"+
					"webappController : "+webappController+" , \n"+
					"}";
	}
		
}
