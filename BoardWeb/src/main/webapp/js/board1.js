/**
 * board1.js
 * XMLHttpRequest, fetch => 실행순서.
 */
/*fetch('replyList.do?bno=' + bno)
	.then(result => result.json())
	.then(successCallback)
	.catch(errorCallback) */


// 함수 선언식.
function successCallback(result){
	console.log(result);
	result.forEach(item => {
		makeRow2(item)
	})
}
// 함수 표현식
/* let successCallback = function successCallback(result){
	console.log(result);
	result.forEach(item => {
		makeRow2(item)
	})
} */


function errorCallback(err){
	console.error(err)
}
// 이벤트.
document.querySelector('button.addReply').addEventListener('click',function(e){
	// 등록.
	if(replyer == ''){
		alert('로그인하세요.');
		return;
	}
	let reply = document.querySelector('#reply').value;
	if(reply == ''){
		alert('댓글을 등록하세요.');
		return;
	}
	console.log(bno,reply,replyer);
	svc.addReply({bno, reply, replyer} // 등록을 위한 첫번째 파라미터
		,function(result){
			if(result.retCode == 'OK'){
				alert('등록성공!');
				let item = result.retVal; // 반환결과값 활용
				makeRow2(item);
				// 입력값 초기화
				document.querySelector('#reply').value = "";
				document.querySelector('#reply').value = "";
			} else {
				alert('등록실패!');
			}
		}, errorCallback);
	
		
});
// bno, replyer, reply: #reply.value 속성.

// 목록 보여주기
svc.replyList(bno, successCallback, errorCallback);
	
// 댓글정보 -> 화면출력
function makeRow2(item){
	let html = `<li data-rno="${item.replyNo}" id="rno_${item.replyNo}">
		<span class="col-sm-2">${item.replyNo}</span>
		<span class="col-sm-5">${item.reply}</span>
		<span class="col-sm-2">${item.replyer}</span>
		<span class="col-sm-2"><button onclick="deleteFnc(${item.replyNo})">삭제</button></span>
		</li>`;
	let templ = document.querySelector('div.content>ul'); 
	templ.insertAdjacentHTML('beforeend',html); //
}

// 삭제함수.
function deleteFnc(rno = 2){
	let deleteOK = confirm("삭제하겠습니까?");
	if(!deleteOK){
		return;
	}
	svc.removeReply(rno
		, function(result){
			console.log(result);
			if(result.retCode =='OK'){
				alert("삭제성공!!");
				document.querySelector(`#rno_${rno}`).remove();
			}
		}
		, function(err){
			errorCallback
		}
	)
}




// 동기, 비동기(Asynchronous Javascript And Xml)
/*setTimeout(function(){
	console.log('1');

	setTimeout(function(){
		console.log('2');
	
		setTimeout(function(){
			console.log('3');
		}, 1000);
	}, 1000);
}, 1000); */


