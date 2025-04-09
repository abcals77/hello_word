package com.yedam.control;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		
		// board_no, replyer, reply 필요
		String bno = req.getParameter("bno");
		String replyer = req.getParameter("replyer");
		String reply = req.getParameter("reply");
		
		// 댓글등록.
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(Integer.parseInt(bno));
		rvo.setReply(reply);
		rvo.setReplyer(replyer);
		rvo.setReplyDate(new Date());
		System.out.println(rvo);
		
		Gson gson = new GsonBuilder().create(); // Gson 사용
		Map<String, Object> map = new HashMap<>();
		
		
		
		// 서비스 호출
		ReplyService svc = new ReplyServiceImpl();
		if(svc.addReply(rvo)) {
//			resp.getWriter().print("{\"retCode\": \"OK\"}");
			map.put("retCode", "OK");
			map.put("retVal", rvo);
		} else {
//			resp.getWriter().print("{\"retCode\": \"NG\"}");
			map.put("retCode", "NG");
		}
		String json = gson.toJson(map);
		resp.getWriter().print(json);
		
	} // end of exec

}
