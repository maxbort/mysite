package com.poscodx.mysite.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.mysite.service.SiteService;
import com.poscodx.mysite.vo.SiteVo;

@Controller
public class MainController {
	
	private SiteService siteService;
	
	public MainController(SiteService siteService) {
		this.siteService = siteService;
	}
	
	@RequestMapping({"/", "/main"})
	public String index(Model model) {
		SiteVo vo = siteService.getSite();
		model.addAttribute("sitevo", vo);
		return "main/index";
	}
	
//	@ResponseBody
//	@RequestMapping("/msg01")
//	public String message01() {
//		return "Hello World";
//	}
//	
//	@ResponseBody
//	@RequestMapping("/msg02")
//	public String message02(String name) {
//		return "안녕~ " + name;
//	}
//
//	@ResponseBody
//	@RequestMapping("/msg03")
//	public Object message03() {
//		UserVo vo = new UserVo();
//		vo.setNo(1L);
//		vo.setName("둘리");
//		vo.setEmail("dooly@gmail.com");
//		
//		return vo; 
//	}
	@RequestMapping("/cookie")
	public String cookie(HttpServletResponse response ,@CookieValue String lang) {
		return "redirect:/";
	}
}

