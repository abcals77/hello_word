/**
 * ajax.js
 */

const xhtp = new XMLHttpRequest();
xhtp.open('get', 'boardList.do'); // 경로지정
xhtp.send();
xhtp.onload = function(){ // load이벤트 발생.
	// 화면출력
	document.querySelector('nav + div.container-fluid').innerHTML = xhtp.responseText;
}

