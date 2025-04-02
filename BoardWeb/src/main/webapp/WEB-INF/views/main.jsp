<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<jsp:include page = "includes/header.jsp"></jsp:include>
<table class="table">
	<tr>
		<td><c:out value="${userName}"/></td>
		<td><c:out value="${responsibility}"/></td>
	</tr>
</table>
<jsp:include page="includes/footer.jsp"></jsp:include>