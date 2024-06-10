package com.poscodx.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			@RequestParam(value="page", required=true, defaultValue="1") Integer currentPage,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword,
			Model model) {
		
		Map<String, Object> map = boardService.getContentsList(currentPage, keyword);
		
		model.addAttribute("map", map);
		model.addAttribute("keyword", keyword);
		
		return "board/list";
	}
	
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.getContents(no);
		model.addAttribute("vo",vo);
		
		return "board/view";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)	
	public String write(HttpSession session) {
		// access control
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		///////////////////////
		return "board/write";
	}

	@RequestMapping(value="/write", method=RequestMethod.POST)	
	public String write(
		HttpSession session,
		@ModelAttribute BoardVo vo,
		@RequestParam(value="page", required=true, defaultValue="1") Integer page,
		@RequestParam(value="kwd", required=true, defaultValue="") String keyword) {
		// access control
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		////////////////////////		
		
		vo.setuserNo(authUser.getNo());
		boardService.addContents(vo);
		
		return	"redirect:/board?page=" + page + "&kwd=" + keyword;
	}
	
	
	@RequestMapping("/modify/{no}")
	public String modify(HttpSession session, @PathVariable("no") Long no, Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			return "redirect:/";
		}
		
		BoardVo vo = boardService.getContents(no,authUser.getNo());
		
		model.addAttribute("vo",vo);
		return "board/update";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)	
	public String modify(
//			HttpSession session, 
			UserVo authUser,
			BoardVo vo,
			@RequestParam(value="page", required=true, defaultValue="1") Integer page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword) {		
			// access control
//			UserVo authUser = (UserVo)session.getAttribute("authUser");
//			if(authUser == null) {
//				return "redirect:/";
//			}
//			////////////////////////
			
			vo.setuserNo(authUser.getNo());
			boardService.updateContents(vo);
			return "redirect:/board/view/" + vo.getNo() + 
					"?page=" + page + 
					"&kwd=" + keyword;
		}
	
	@RequestMapping("/delete/{no}")
	public String delete(HttpSession session,
			@PathVariable("no") Long no,
			@RequestParam(value="page", required=true, defaultValue="1") Integer page,
			@RequestParam(value="kwd", required=true, defaultValue="") String keyword) {
		// access control
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		boardService.deleteContents(no, authUser.getNo());
		
		return "redirect:/board?page=" + page + "&keyword="+keyword;
	}
	
	
	@RequestMapping("/reply/{no}")
	public String reply(HttpSession session,
			@PathVariable("no") Long no,
			Model model) {
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		
		BoardVo vo = boardService.getContents(no);
		vo.setOno(vo.getOno() + 1);
		vo.setDepth(vo.getDepth() + 1);
		
		boardService.addContents(vo);
		
		return "board/write";
	}
	
	
	
	
	
}
