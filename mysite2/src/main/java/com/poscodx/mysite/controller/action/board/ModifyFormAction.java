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

public class ModifyFormAction implements Action{

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
		String r_no = request.getParameter("no");
		BoardVo vo = new BoardVo();
		String no = request.getParameter("no");
		Long l_no = Long.parseLong(no);
		vo = new BoardDao().findView(l_no);
		request.setAttribute("info", vo);
		request.setAttribute("vo", authUser);
		request.setAttribute("r_no", r_no);
		request.setAttribute("page_no", Integer.parseInt(request.getParameter("page_no")));
		request.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(request, response);

	}

}
