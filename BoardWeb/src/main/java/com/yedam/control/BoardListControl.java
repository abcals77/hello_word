package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 글목록정보 -> jsp
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> list = mapper.selectBoard();
		req.setAttribute("blist", list); // blist에 list 객체를 담음
		// boardList.do - jsp 출력. : 페이지재지정.
		// boardList로 blist를 보냄
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
		
	}

	
	
}
