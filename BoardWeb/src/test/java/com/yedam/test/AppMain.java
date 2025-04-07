package com.yedam.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.ReplyMapper;
import com.yedam.vo.ReplyVO;

public class AppMain {
	public static void main(String[] args) {
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		ReplyMapper mapper = sqlSession.getMapper(ReplyMapper.class);
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(152);
		rvo.setReply("댓글테스트");
		rvo.setReplyer("user02");
		
		int cnt = mapper.insertReply(null);
		if(cnt > 0) {
			System.out.println("성공");
		}
		
		List<ReplyVO> list = mapper.selectList(152);
		for(ReplyVO reply : list) {
			System.out.println(reply.toString());
		}
	}
}
