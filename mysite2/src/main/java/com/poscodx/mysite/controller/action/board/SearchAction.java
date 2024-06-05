package com.poscodx.mysite.controller.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.controller.ActionServlet.Action;
import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("kwd");
		System.out.println(keyword);
		if (keyword == null) {
			keyword = "";
		}
		
		List<BoardVo> list = new BoardDao().findAll(keyword,0);

		request.setAttribute("list", list);


		request
		.getRequestDispatcher("/WEB-INF/views/board/list.jsp")
		.forward(request, response);
	}

}
