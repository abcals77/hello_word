<%@page import="com.yedam.common.PageDTO"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<!-- webapp/WEB-INF/views/boardList.jsp -->
<%
List<BoardVO> list = (List<BoardVO>) request.getAttribute("blist"); // blist에 담긴 정보를 가져옴
PageDTO pageDTO = (PageDTO) request.getAttribute("paging");
String sc = (String) request.getAttribute("searchCondition");
String kw = (String) request.getAttribute("keyword");
%>
<p><%=pageDTO %></p>
<h3>게시글 목록(boardList.jsp)</h3>
<!-- 검색조건. -->
<form action = "boardList.do">
<div class = "row">
<%
	String[][] options = {
	    {"T", "제목"},
	    {"W", "작성자"},
	    {"TW", "제목&작성자"}
	};
%>

<div class="col-sm-4">
 	<select name="searchCondition" class="form-control">
    	<option value="">선택하세요</option>
    	<% for (String[] option : options) {
        	String val = option[0];
        	String label = option[1];
        	boolean selected = val.equals(sc);
    	%>
      		<option value="<%=val%>" <%= selected ? "selected" : "" %>><%=label%></option>
    	<% } %>
 	</select>
</div>
	<div class = "col-sm-6">
			<input type = "text" name = "keyword" class = "form-control" value = <%= kw == null ? "" : kw %>>
	</div>
	<div class = "col-sm-2">
		<button type = "submit" class = "btn btn-info">검색</button>	
	</div>
</div>
</form>
<table class="table">
	<thead>
		<tr>
			<th>글번호</th>
			<th>제 목</th>
			<th>작성자</th>
			<th>작성일시</th>
		</tr>
	</thead>
	<tbody>
		<%
		for (BoardVO board : list) {
		%>
		<tr>
			<td><%=board.getBoardNo()%></td>
			<td><a href='board.do?page=<%=pageDTO.getCurrentPage() %>&bno=<%=board.getBoardNo()%>'><%=board.getTitle()%></a></td>
			<td><%=board.getWriter()%></td>
			<td><%=board.getWriteDate()%></td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>
<!-- paging 처리 -->
<nav aria-label="...">
	<ul class="pagination">
	<!-- 이전 10개 페이지여부. -->
		<%if(pageDTO.isPrev()){ %>
		<li class="page-item">
			<a class="page-link" href="boardList.do?page=1&searchCondition=<%=sc %>&keyword=<%=kw %>">첫페이지</a>
		</li>
		<li class="page-item"> <!-- 11페이지에서 10페이지로 이동 -->
			<a class="page-link" href="boardList.do?page=<%=pageDTO.getStartPage() - 1 %>&searchCondition=<%=sc %>&keyword=<%=kw %>">Next</a>
		</li>
		<%} else {%>
		<li class="page-item disabled">
			<span class="page-link">Previous</span>
		</li>
		<%} %>
		<% for (int p = pageDTO.getStartPage(); p <= pageDTO.getEndPage(); p++){%>
		<% if (pageDTO.getCurrentPage() == p) { %>
		<li class="page-item active" aria-current="page"><span
			class="page-link"><%=p %></span></li>
		<%} else{ %>
		<li class="page-item"><a class="page-link" href="boardList.do?page=<%=p %>&searchCondition=<%=sc %>&keyword=<%=kw %>"><%=p %></a></li>
		<% }
		} %>
		<!-- 이후 10개 페이지여부. -->
		<%if(pageDTO.isNext()){ %>
		<li class="page-item"> <!-- 1페이지에서 11페이지로 이동 -->
			<a class="page-link" href="boardList.do?page=<%=pageDTO.getEndPage() + 1 %>&searchCondition=<%=sc %>&keyword=<%=kw %>">Next</a>
		</li>
		<li class="page-item">
			<a class="page-link" href="boardList.do?page=<%=pageDTO.getRealEnd() %>&searchCondition=<%=sc %>&keyword=<%=kw %>">마지막</a>
		</li>
		<%} else {%>
		<li class="page-item disabled">
			<span class="page-link">Next</span>
		</li>
		<%} %>
	</ul>
</nav>
<jsp:include page="includes/footer.jsp"></jsp:include>