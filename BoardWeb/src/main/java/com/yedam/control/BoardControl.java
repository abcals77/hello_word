package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// parameter : ?bno = 9
		String boardNo = req.getParameter("bno");
		String page = req.getParameter("page");
		
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		
		BoardVO board = mapper.selectContent(Integer.parseInt(boardNo));
		req.setAttribute("cboard", board);
		req.setAttribute("page", page);
		
		
		// board.jsp 전달.
		req.getRequestDispatcher("/WEB-INF/views/board.jsp").forward(req, resp);
	}
	
}
