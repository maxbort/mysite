package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

public class WriteAction implements Action{

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
		
		request.setAttribute("vo", authUser);
		
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		BoardVo vo1 = new BoardVo();
		
		vo1.setTitle(title);
		vo1.setContents(contents);
		
		Long no = (authUser.getNo());
		vo1.setUser_no(no);
		
		new BoardDao().insert(vo1);
		System.out.println(vo1);
		response.sendRedirect(request.getContextPath() +"/board");
	}

}
