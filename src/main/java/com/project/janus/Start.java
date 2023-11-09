package com.project.janus;

import java.io.File;
import java.io.IOException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import jakarta.servlet.ServletException;

import com.project.janus.framework.servlets.WebServlet;
import com.project.janus.framework.webapp.WebAppLoader;

public class Start {
	
	
    public static void main(String[] args)
            throws LifecycleException, InterruptedException, ServletException {
    	
    	String configPath = System.getProperty("user.dir")+"/config.json";
    	try {
    		WebAppLoader.getInstance(configPath);
    		WebAppLoader.getWebApps();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context ctx = tomcat.addContext("/", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "hello", new WebServlet());
        
        ctx.addServletMappingDecoded("/*", "hello");
        tomcat.start();
        tomcat.getServer().await();
    }

}
