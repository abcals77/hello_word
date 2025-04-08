package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class ReplyCountControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 게시글번호에 대한 댓글수를 반환.
		String bno = req.getParameter("bno");
		// 건수.
		ReplyService svc = new ReplyServiceImpl();
		int cnt = svc.getTotalCnt(Integer.parseInt(bno));
		// {"totalCnt": 25}
		resp.getWriter().print("{\"totalCnt\": "+ cnt +"}");
		
		
	}

}
