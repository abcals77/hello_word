package com.yedam.mapper;


import java.util.List;

import com.yedam.vo.EventVO;

public interface EventMapper {
	
	int insertEvent(EventVO evo); // 이벤트등록.
	List<EventVO> selectList();
	int deleteEvent(EventVO evo);

}
