package com.poscodx.mysite.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {

 
    public void contextDestroyed(ServletContextEvent sce)  { 
    	
    }

	
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext sc = sce.getServletContext();
    	String contextConfigLocation = sc.getInitParameter("conte!!!````;");
    	
    	
    	System.out.println("Application[Mysite2] starts..." + contextConfigLocation);
    }
	
}
