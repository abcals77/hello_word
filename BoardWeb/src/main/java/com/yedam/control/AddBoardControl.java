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

public class AddBoardControl implements Control{

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equals("GET")) {
			// 요청재지정. // get방식요청 : url에 직접 입력, 링크
			req.getRequestDispatcher("board/addForm.tiles").forward(req, resp);
		} else if(req.getMethod().equals("POST")) {
			// 등록
			req.setCharacterEncoding("UTF-8");
			
			String title = req.getParameter("title"); // name = title 입력칸에 담긴 정보를 가져옴
			String writer = req.getParameter("writer");
			String content = req.getParameter("content");
			
			BoardVO board = new BoardVO();
			board.setTitle(title);
			board.setWriter(writer);
			board.setContent(content);
			
			// mybatis를 활용해서 jbdc 처리.
			SqlSession sqlSession = DataSource.getInstance().openSession(true); // true : 자동커밋
			BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
			int r = mapper.insertBoard(board);
			if(r < 0) {
				System.out.println("에러발생");
			}
			resp.sendRedirect("boardList.do"); // 요청재지정
			
			
		}
		
		
	}

}
