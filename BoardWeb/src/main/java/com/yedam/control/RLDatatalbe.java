package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class RLDatatalbe implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// {"data":[ [21, '댓글내용', 'user01', '날짜'], [], []... [] ]}
		String bno = req.getParameter("bno");
		ReplyService svc = new ReplyServiceImpl();
		
		
	}

}
