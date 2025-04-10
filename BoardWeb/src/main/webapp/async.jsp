<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/async.jsp</title>
</head>
<body>
	<button id="button">버튼</button>
	<script>
	// 비동기코드 -> 동기코드.
	document.querySelector('#button').addEventListener('click', async function(){
		
		console.log(1);
		let result = await fetch('eventList.do');
		console.log(2);
		let resolve = await result.json();
		console.log(resolve);
					
	})
	
	
	
	
	</script>
</body>
</html>