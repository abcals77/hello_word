package com.yedam.service;

import java.util.List;

import com.yedam.vo.EventVO;

public interface EventService {

	boolean addEvent(EventVO evo);
	List<EventVO> eventList();
	boolean deleteEvent(EventVO evo);
}
