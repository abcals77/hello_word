package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;
import com.yedam.vo.EventVO;

public class AddEventControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");
		String title = req.getParameter("title");
		String startDate = req.getParameter("startDate");
		String endDate = req.getParameter("endDate");
		
		

		// 댓글등록.
		EventVO evo = new EventVO();
		evo.setTitle(title);
		evo.setStartDate(startDate);
		evo.setEndDate(endDate);
		System.out.println(evo);

		Gson gson = new GsonBuilder().create(); // Gson 사용
		Map<String, Object> map = new HashMap<>();

		// 서비스 호출
		EventService svc = new EventServiceImpl();
		if (svc.addEvent(evo)) {
			map.put("retCode", "OK");
			map.put("retVal", evo);
		} else {
			map.put("retCode", "NG");
		}
		String json = gson.toJson(map);
		resp.getWriter().print(json);

	}

}
