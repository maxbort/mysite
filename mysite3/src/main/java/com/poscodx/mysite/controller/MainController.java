package com.poscodx.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping({"/", "/main"})
	public String index() {
		
//		ServletContext sc = request.getServletContext();
		
//		Enumeration<String> names = (Enumeration<String>)sc.getAttributeNames();
//		
//		while(names.hasMoreElements()) {
//			String name = names.nextElement();
//		}
//		
//		ApplicationContext a1 = (ApplicationContext)sc.getAttribute("org.springframework.web.context.WebapplicationContext.ROOT");
//		ApplicationContext a2 = (ApplicationContext)sc.getAttribute("org.springframework.web.context.WebapplicationContext.ROOT");
//		
//		System.out.println(a2);
//		System.out.println(a1);
		return "main/index";
	}

}
