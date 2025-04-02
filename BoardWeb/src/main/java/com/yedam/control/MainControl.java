package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession session = req.getSession();
		String logId = (String)session.getAttribute("logId");
		if(logId == null) {
			resp.sendRedirect("loginForm.do");
		} else {
			req.getRequestDispatcher("WEB-INF/views/main.jsp").forward(req, resp);
		}
	
	}

}
