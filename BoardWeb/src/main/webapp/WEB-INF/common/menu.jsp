<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
    <style>
		.iconimage{
			border-radius:50%;
			width:30px;
			height:30px;
		}
		.noimage{
			height:0;
			width:0;
		}
	</style>
	<%
    	String logId = (String) session.getAttribute("logId");
    %>
            <div class="border-end bg-white" id="sidebar-wrapper">
                <a href="main.do" class="sidebar-heading border-bottom bg-light d-block text-decoration-none text-dark">
  		  			시작페이지
				</a>
                <div class="list-group list-group-flush">
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="boardList.do">글목록</a>
                    <%if (logId == null) {%>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="loginForm.do">로그인</a>
                    <%} else {%>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="addBoard.do">등록화면</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="logout.do">로그아웃(<%=logId %>)</a>
					<%} %>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="signForm.do">회원가입</a>
                    <a class="list-group-item list-group-item-action list-group-item-light p-3" href="javascript.do">js연습</a>
                	<a class="list-group-item list-group-item-action list-group-item-light p-3" href="eventForm.do">이벤트</a>
                </div>
            </div>