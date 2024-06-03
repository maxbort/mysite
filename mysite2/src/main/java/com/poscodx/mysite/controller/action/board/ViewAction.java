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

public class ViewAction implements Action{
	
	
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
		System.out.println("cur_page = " + current_page);
		
		
		
		
		String no = request.getParameter("no");
		Long no1 = Long.parseLong(no);
		BoardVo vo = new BoardDao().findView(no1);
		new BoardDao().hitUp(vo);
		request.setAttribute("vo", vo);
		request.setAttribute("authUser", authUser);
		request.setAttribute("page_no", current_page);
		request.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(request, response);

	}

}
