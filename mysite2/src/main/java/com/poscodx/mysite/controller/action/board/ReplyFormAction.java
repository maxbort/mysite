package com.poscodx.mysite.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.mysite.vo.UserVo;

public class ReplyFormAction implements Action {

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
		
		BoardVo bvo = new BoardVo();
		
		Long no = Long.parseLong(request.getParameter("no"));
		Integer gno = Integer.parseInt(request.getParameter("g_no"));
		Integer ono = Integer.parseInt(request.getParameter("o_no"));
		Integer depth = Integer.parseInt(request.getParameter("depth"));

		bvo.setNo(no);
		bvo.setG_no(gno);
		bvo.setO_no(ono);
		bvo.setDepth(depth);
		
		
		request.setAttribute("vo", authUser);
		request.setAttribute("bvo", bvo);
		

		request.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(request, response);
	}

}
