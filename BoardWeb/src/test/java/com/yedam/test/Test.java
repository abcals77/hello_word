package com.yedam.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class Test {
	public static void main(String[] args) {
		SqlSessionFactory sqlSessionFactory = DataSource.getInstance();
		BoardVO board = new BoardVO();
		board.setTitle("매퍼테스트44");
		board.setContent("매퍼를 활용한 입력테스트");
		board.setWriter("newbie");
		board.setBoardNo(8);
		
		
		try(SqlSession sqlSession = sqlSessionFactory.openSession()){
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			
			List<BoardVO> list = mapper.selectBoard(null);
			for(BoardVO brd : list) {
				System.out.println(brd.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
