package com.yedam.web;
// form태그(jsp) -> 서블릿
// 서블릿 -> jsp

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

@WebServlet("/getBoard")
public class GetBoardServ extends HttpServlet {
	// http://localhost/BoardWeb/getBoard?board_no=13
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String boardNo = req.getParameter("board_no");
		
		SqlSession sqlSession = DataSource.getInstance().openSession(true); // true : 자동커밋
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		BoardVO board = mapper.selectContent(Integer.parseInt(boardNo));
		
		PrintWriter out = resp.getWriter();
		String html = "<h3>상세조회</h3>";
		html += "<p>글번호 : " + board.getBoardNo() +"</p>";
		html += "<p>제목 : " + board.getTitle() +"</p>";
		html += "<p>내용 : " + board.getContent() +"</p>";
		html += "<p>작성자 : " + board.getWriter() +"</p>";
		html += "<p>일자 : " + board.getWriteDate() +"</p>";
		
		html +="<p><a href = 'mainsevlet'>게시판</a></p>";
		out.print(html);
	}
}
