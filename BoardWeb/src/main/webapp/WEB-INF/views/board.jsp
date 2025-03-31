<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page = "includes/header.jsp"></jsp:include>
	<!-- webapp/WEB-INF/views/board.jsp -->
	<%
		BoardVO board = (BoardVO) request.getAttribute("cboard");
	%>
	<h3>상세페이지(board.jsp)</h3>
	<table class = "table">
		<tr>
			<th>글번호</th><td><%=board.getBoardNo() %></td>
			<th>작성자</th><td><%=board.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan = "3"><%=board.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan = "3"><textarea class="form-control" cols = "30" rows = "3" readonly><%=board.getContent() %></textarea></td>
		</tr>
		<tr>
			<th>작성일시</th>
			<td colspan = "3"><%=board.getWriteDate() %></td>
		</tr>
	</table>
	<p><a href = 'boardList.do'>목록이동</a></p>
<jsp:include page="includes/footer.jsp"></jsp:include>