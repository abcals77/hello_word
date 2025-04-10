package com.yedam.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.DataSource;
import com.yedam.mapper.EventMapper;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class AppMain {
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession();
		EventMapper mapper = sqlSession.getMapper(EventMapper.class);
		
		List<Map<String, Object>> list = mapper.selectWriter();
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(list);
		System.out.println(json);
		
	}
}
