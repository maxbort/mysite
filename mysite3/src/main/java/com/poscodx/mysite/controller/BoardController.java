package com.poscodx.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poscodx.mysite.service.BoardService;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("")
	public String index(
			@RequestParam(value="page_no", required=true, defaultValue="1") Integer currentPage,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model) {
		
		Map<String, Object> map = boardService.getContentsList(currentPage, keyword);
		
		model.addAttribute("map", map);
		model.addAttribute("keyword", keyword);
		
		return "board/list";
	}
	
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.getCotents(no);
		
		return "board/view";
	}
	
	
	@RequestMapping("/modify/{no}")
	public String modify(@PathVariable("no") Long no) {
		return "board/modify";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(HttpSession session, @PathVariable("no") Long no) {
		// access control
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		return "board/view";
	}
	
	@RequestMapping("/write/{no}")
	public String add(HttpSession session, @PathVariable("no") Long no) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		return "board/write";
	}
	
	@RequestMapping("/reply/{no}")
	public String reply(HttpSession session, @PathVariable("no") Long no) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		return "";
	}
	
	
	
	
	
}
