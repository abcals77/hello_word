/**
 * board.js
 */
console.log(bno);
const xhtp = new XMLHttpRequest();
xhtp.open('get', 'replyList.do?bno=' + bno);
xhtp.send();
xhtp.onload = function(){
	console.log(xhtp.responseText); // json문자열.
	let data = JSON.parse(xhtp.responseText);
	console.log(data); // object.
	data.forEach(function(item){
		makeRow2(item)
	})
function makeRow(item) {
	// template 가져오기
	let templ = document.querySelector('div.content>ul>li').cloneNode(true); // clone : 찾는 대상을 복사 true : 하위 요소까지 복사 false : 찾는 대상만 복사
	console.log(templ);
	templ.querySelector('span:nth-of-type(1)').innerHTML = item.replyNo;
	templ.querySelector('span:nth-of-type(2)').innerHTML = item.reply;
	templ.querySelector('span:nth-of-type(3)').innerHTML = item.replyer;
	templ.querySelector('span:nth-of-type(4)').innerHTML = '<button>삭제</button>';
	document.querySelector('div.content>ul').appendChild(templ);	
}
function makeRow2(item){
	let html = `<li>
					<span class="col-sm-2">${item.replyNo}</span>
					<span class="col-sm-5">${item.reply}</span>
					<span class="col-sm-2">${item.replyer}</span>
					<span class="col-sm-2"><button>삭제</button></span>
				</li>`;
	let templ = document.querySelector('div.content>ul'); 
	templ.insertAdjacentHTML('beforeend',html); //
	 
}
	
	
	
}// end of onload