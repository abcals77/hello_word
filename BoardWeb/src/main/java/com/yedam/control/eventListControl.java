package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.EventService;
import com.yedam.service.EventServiceImpl;
import com.yedam.vo.EventVO;

public class eventListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("application/json;charset=utf-8");

	        EventService svc = new EventServiceImpl();
	        List<EventVO> list = svc.eventList();

	        Gson gson = new GsonBuilder().create();
	        resp.getWriter().print(gson.toJson(list));
		
	}

}
