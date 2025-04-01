<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- modifyBoard.jsp -->
<jsp:include page="includes/header.jsp"></jsp:include>
<h3>삭제화면(deleteForm.jsp)</h3>
<%
BoardVO board = (BoardVO) request.getAttribute("dboard");
String paging = (String) request.getAttribute("page");
%>
<form action="deleteBoard.do" onsubmit="return confirm('정말 삭제하시겠습니까?');">
	<input type="hidden" name="bno" value="<%=board.getBoardNo()%>">
	<input type = "hidden" name = "page" value = "<%=paging %>">
	<table class="table">
		<tr>
			<th>글번호</th>
			<td><%=board.getBoardNo()%></td>
			<th>작성자</th>
			<td><%=board.getWriter()%></td>
		</tr>
		<tr>
			<th>제목</th>
			<td colspan="3"><%=board.getTitle()%></td>
		</tr>
		<tr>
			<th>내용</th>
			<td colspan="3"><textarea class="form-control" cols="30"
					rows="3" readonly><%=board.getContent()%></textarea></td>
		</tr>
		<tr>
			<th>작성일시</th>
			<td colspan="3"><%=board.getWriteDate()%></td>
		</tr>
		<tr>
			<td colspan="3" align="center"><input type="submit" value="삭제"
				class="btn btn-danger"></td>
		</tr>
	</table>
</form>
<jsp:include page="includes/footer.jsp"></jsp:include>