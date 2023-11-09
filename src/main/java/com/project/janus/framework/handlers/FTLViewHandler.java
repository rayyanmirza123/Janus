package com.project.janus.framework.handlers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
public class FTLViewHandler {
	private static Configuration configuration;
	static {
		configuration = new Configuration();
		try {
			configuration.setTemplateLoader(new MultiTemplateLoader(new TemplateLoader[] { 
					new FileTemplateLoader(new File("runtime")) 
			}));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void render(String viewLocation, HttpServletRequest request, HttpServletResponse response, Map<String,Object> context) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		
		Template template = configuration.getTemplate(viewLocation);
		PrintWriter writer = response.getWriter();
		
		context.put("request", request);
		//context.put("response", request.getAttr);
		
		List<String> attributeNames = Collections.list(request.getAttributeNames());
		
		for(String attrName : attributeNames) {
			context.put(attrName, request.getAttribute(attrName));
		}
		
		context.put("response", response);
		
		template.process(context, writer);
		
	}
	
}
