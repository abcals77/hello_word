package com.yedam.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.EventMapper;
import com.yedam.vo.EventVO;

public class EventServiceImpl implements EventService {
	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	EventMapper mapper = sqlSession.getMapper(EventMapper.class);
	
	@Override
	public boolean addEvent(EventVO evo) {

		return mapper.insertEvent(evo) == 1;
	}
	@Override
	public boolean deleteEvent(EventVO evo) {
	    
		return mapper.deleteEvent(evo) == 1;
	}
	
	@Override
	public List<EventVO> eventList() {
	    return mapper.selectList();
	}
}
