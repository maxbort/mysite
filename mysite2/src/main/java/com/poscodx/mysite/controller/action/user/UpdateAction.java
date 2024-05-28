package com.poscodx.mysite.controller.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;

public class UpdateAction implements Action {


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath());
			return;
		}
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String no1 = request.getParameter("no");
		
		UserVo vo = new UserVo();
		Long no = Long.parseLong(no1);
		vo.setName(name);
		vo.setEmail(email);
		vo.setNo(no);
		if (password == null) {
			vo.setPassword(vo.getPassword());
		}
		else {
			vo.setPassword(password);
		}
		vo.setGender(gender);

		new UserDao().update(vo);
		authUser.setName(name);
		
		response.sendRedirect(request.getContextPath()+"/user?a=updateform&result=success");
	}

}
