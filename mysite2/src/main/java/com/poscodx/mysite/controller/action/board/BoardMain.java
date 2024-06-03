package com.poscodx.mysite.controller.action.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;


public class BoardMain implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session == null) {
			response.sendRedirect(request.getContextPath());
		}
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser == null) {
			response.sendRedirect(request.getContextPath()+"/user");
			return;
		}
		
		int current_page = Integer.parseInt(request.getParameter("page_no"));
	
		List<BoardVo> list = new BoardDao().findAll("",(current_page-1)*5);
		
		System.out.println(list);
		System.out.println(current_page);
		List<Integer> page_list = new ArrayList<>();
	
		int total_page = 0;
		if (new BoardDao().findTotal().size() / 5 == 0) {
			total_page = new BoardDao().findTotal().size() / 5;
		} else {
			total_page = new BoardDao().findTotal().size() / 5 + 1;
		}
		
		List<BoardVo> list1 = new BoardDao().findAll("",0);
		List<BoardVo> list2 = new BoardDao().findAll("",5);
		System.out.println(list1);
		System.out.println(list2);
		
		for(int i = 1; i <= total_page; i++) {
			page_list.add(i);
		}
		
				
		request.setAttribute("list", list);
		request.setAttribute("authUser", authUser);
		request.setAttribute("page_list", page_list);
		request.setAttribute("last_page", total_page);
		request.setAttribute("current_page",current_page);
		request
			.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
			.forward(request, response);
	}
	
}
