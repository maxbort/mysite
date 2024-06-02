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

public class ReplyAction implements Action {

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
		Integer g_no = Integer.parseInt(request.getParameter("g_no"));
		Integer o_no = Integer.parseInt(request.getParameter("o_no"));
		Integer depth = Integer.parseInt(request.getParameter("depth"));
		
		o_no += 1;
		
		new BoardDao().replyBeforeUpdate(g_no,o_no);
		BoardVo vo1 = new BoardVo();
		vo1.setTitle(title);
		vo1.setContents(contents);
		vo1.setG_no(g_no);
		vo1.setO_no(o_no);
		vo1.setDepth(depth+1);
		
		Long no = (authUser.getNo());
		vo1.setUser_no(no);
		
		new BoardDao().reply(vo1);
		response.sendRedirect(request.getContextPath() +"/board");
	}

}
