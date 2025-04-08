package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 페이지 parameter
		String page = req.getParameter("page");
		page = page == null ? "1":page;
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		
		SearchDTO search = new SearchDTO();
		search.setKeyword(kw);
		search.setPage(Integer.parseInt(page));
		search.setSearchCondition(sc);
		
		
		// 글목록정보 -> jsp
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> list = mapper.selectBoard(search);
		
		req.setAttribute("blist", list); // blist에 list 객체를 담음
		// 페이징계산.
		int totalCnt = mapper.selectTotal(search);
		PageDTO pageDTO = new PageDTO(totalCnt,Integer.parseInt(page));
		req.setAttribute("paging", pageDTO);
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		
		// boardList.do - jsp 출력. : 페이지재지정.
		// boardList로 blist를 보냄
		req.getRequestDispatcher("board/boardList2.tiles").forward(req, resp);
		
	}

	
	
}
