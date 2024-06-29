package com.poscodx.mysite.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscodx.mysite.dto.JsonResult;
import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo vo) {
		return "user/join";
	}
	
	@GetMapping("/checkemail")
	public JsonResult checkEmail(@RequestParam(value="email", required=true, defaultValue = "") String email) {
		UserVo vo = userService.getUser(email);
	
		return JsonResult.success(vo != null);
	}

	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model){
		if(result.hasErrors()) {

			Map<String, Object> map = result.getModel();

			model.addAllAttributes(map);
			
			return "user/join";
		}
		
		userService.join(vo);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Authentication authentication, Model model) {
//      1. SecurityContextHolder(Spring Security ThreadLocal Helper Class) 기반		
//		SecurityContext sc = SecurityContextHolder.getContext();
//		Authentication authentication = sc.getAuthentication();
//      2. HttpSession 기반		
//		SecurityContext sc = (SecurityContext)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY)
//		Authentication authentication = sc.getAuthentication();
		UserVo authUser = (UserVo)authentication.getPrincipal();
		UserVo vo = userService.getUser(authUser.getNo());
		model.addAttribute("userVo", vo);
		
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(Authentication authentication, UserVo userVo) {
		UserVo authUser = (UserVo)authentication.getPrincipal();
		userVo.setNo(authUser.getNo());
		
		userService.update(userVo);
		
		authUser.setName(null);
		return "redirect:/user/update";	}
	

}