package com.yedam.mapper;


import java.util.List;
import java.util.Map;

import com.yedam.vo.EventVO;

public interface EventMapper {
	
	int insertEvent(EventVO evo); // 이벤트등록.
	List<EventVO> selectList();
	int deleteEvent(EventVO evo);
	// 차트.
	List<Map<String, Object>> selectWriter();
	// 로그.
	int insertLogging(Map<String, String> map);
}
