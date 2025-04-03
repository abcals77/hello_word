<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
    	String logId = (String) session.getAttribute("logId");
    %>
	<!-- addForm  -->
	<form action="addBoard.do" method = "post"> <!-- // action : submit할 경우 이동할 페이지 -->
		<table class = "table">
			<tr>
				<th>글제목</th><td><input class="form-control" type = "text" name = "title"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=logId %>
					<input class="form-control" type = "hidden" name = "writer" value = <%=logId %>>
				</td>
			</tr>
			<tr>
				<th>본문</th><td><textarea class="form-control" name = "content" rows = "3" cols = "40"></textarea></td>
			</tr>
			<tr>
				<td colspan = "2" align = "center">
					<input type = "submit" class = "btn btn-primary">
					<input type = "reset" class = "btn btn-secondary">
				</td>
			</tr>
		</table>
	</form>
