package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.UserVo;

public class DeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session == null) {
			response.sendRedirect(request.getContextPath());
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user");
			return;
		}

		String no = request.getParameter("no");
		String password = request.getParameter("user_no");

		new BoardDao().deleteByNo(Long.parseLong(no), authUser.getNo());
		int current_page = Integer.parseInt(request.getParameter("page_no"));
		
		
		response.sendRedirect(request.getContextPath() + "/board?page_no=1");
	}

}
