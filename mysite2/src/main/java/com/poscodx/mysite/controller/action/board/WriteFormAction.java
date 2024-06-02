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

public class WriteFormAction implements Action{

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
//		
		BoardVo vo = new BoardVo();
		
		vo.setUser_no(authUser.getNo());

		request.setAttribute("vo", vo);
//		String title = request.getParameter("title");
//		String contents = request.getParameter("content");
//		
//		BoardVo vo1 = new BoardVo();
//		
//		vo1.setTitle(title);
//		vo1.setContents(contents);
//		
//		//Long no = Long.parseLong(request.getParameter("no"));
//		
//		new BoardDao().insert(vo);
		request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);
	}

}
