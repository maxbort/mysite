package com.poscodx.mysite.controller;

import java.util.Map;

import com.poscodx.mysite.controller.action.guestbook.AddAction;
import com.poscodx.mysite.controller.action.guestbook.DeleteAction;
import com.poscodx.mysite.controller.action.guestbook.DeleteFormAction;
import com.poscodx.mysite.controller.action.guestbook.GuestMain;


public class GuestbookServlet extends ActionServlet {
	private static final long serialVersionUID = 1L;

	
	private Map<String, Action> mapAction = Map.of(
			"add", new AddAction(),
			"deleteform", new DeleteFormAction(),
			"delete", new DeleteAction(),
			"list", new GuestMain()
			);
			
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	
//	
//	
//		request.setCharacterEncoding("utf-8");
//
//		String action = request.getParameter("a");
//		if("add".equals(action)) {
//			String name = request.getParameter("name");
//		   String password = request.getParameter("password");
//		   String content = request.getParameter("contents");
//			GuestBookVo vo = new GuestBookVo();
//			vo.setName(name);
//			vo.setPassword(password);
//			vo.setContent(content);
//
//			new GuestBookDao().insert(vo);
//			response.sendRedirect(request.getContextPath() + "/guestbook");			
//		} else if("delete".equals(action)) {
//				
//				String no = request.getParameter("no");
//			   String password = request.getParameter("password");
//			   
//			   new GuestBookDao().deleteByNo(Long.parseLong(no),password);
//			   
//			response.sendRedirect(request.getContextPath() +"/guestbook");			
//		} else if("deleteform".equals(action)) {
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
//			rd.forward(request, response);
//		}
//		else {
//			/* default action (list) */
//			List<GuestBookVo> list = new GuestBookDao().findAll();
//			request.setAttribute("list", list);
//			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/list.jsp");
//			rd.forward(request, response);			
//		}
//	}
	
	@Override	
	protected Action getAction(String actionName) {

		return mapAction.getOrDefault(actionName,  new GuestMain());
	}



}
