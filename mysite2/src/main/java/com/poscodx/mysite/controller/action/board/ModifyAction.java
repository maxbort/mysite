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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// Access Control
		if (session == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String no = request.getParameter("no");
		if (authUser == null || !no.matches("[0-9]+")) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		String title = request.getParameter("title");
		String contents = request.getParameter("content");
		
		if (title == null || "".equals(title)) {
			response.sendRedirect(request.getContextPath());
			return;
		}

		BoardVo vo = new BoardVo();
		vo.setNo(Long.parseLong(no));
		vo.setTitle(request.getParameter("title"));
		vo.setContents(request.getParameter("contents"));
		vo.setUser_no(authUser.getNo());

		new BoardDao().update(vo);

		response.sendRedirect(request.getContextPath() + "/board?a=view&no=" + no);
	}

	

}
