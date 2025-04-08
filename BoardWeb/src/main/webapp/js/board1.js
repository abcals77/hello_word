/**
 * board1.js
 * XMLHttpRequest, fetch => 실행순서.
 */
let page = 1;

/*fetch('replyList.do?bno=' + bno)
	.then(result => result.json())
	.then(successCallback)
	.catch(errorCallback) */


// 함수 선언식.
function successCallback(result){
	console.log(result);
	// 기존목록 지우기.
	// document.querySelectorAll('div.reply ul.content > li')
	document.querySelectorAll('div.reply div.content>ul>li')
		.forEach(function(item, idx){
			if(idx > 0){ // truthy, falsy
				item.remove();
			}
		}); 
	// 새로운 목록 출력.
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
	console.error()
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
				
				// 목록 보여주기
				page = 1;
				setTimeout(() => {
				svc.replyList({bno, page}, successCallback, errorCallback);
				}, 0);
				// 페이징 목록 보여주기
				setTimeout(() => {
					svc.pagingList(bno, pagingCallBack, errorCallback);
				}, 0);

								// 입력값 초기화
				document.querySelector('#reply').value = "";
				document.querySelector('#reply').value = "";
				
				
			} else {
				alert('등록실패!');
			}
		}, errorCallback);
	
		
});
// bno, replyer, reply: #reply.value 속성.

// 페이지 링크에 이벤트 등록.
function pageLink(){
	document.querySelectorAll('div.reply ul a')
		.forEach(function(atag){
			//console.log(atag);
			atag.addEventListener('click', function(e) {
				e.preventDefault(); // 이벤트의 기본기능 차단.
				page = atag.dataset.page; // <a>3</a>
				console.log(page);

				// 댓글목록
				svc.replyList({bno, page}, successCallback, errorCallback);
				// 페이징목록
				svc.pagingList(bno, pagingCallBack, errorCallback);
								
			})
		});
}

// 목록 보여주기
svc.replyList({bno, page}, successCallback, errorCallback);

// 페이징 목록 보여주기
svc.pagingList(bno, pagingCallBack, errorCallback);

// 페이징 콜백.
function pagingCallBack(result){
	// 댓글목록 초기화
	document.querySelector('nav>ul.pagination').innerHTML = "";
	
	console.log(result.totalCnt);
	let totalCnt = result.totalCnt;
	// 첫페이지, 마지막페이지 => 현재페이지로 계산
	let startPage, endPage;
	// 이전페이지, 이후페이지
	let prev, next;
	endPage = Math.ceil(page / 10) * 10;
	startPage = endPage - 9;
	let realEnd = Math.ceil(totalCnt / 5);
	endPage = endPage > realEnd ? realEnd : endPage;
	prev = startPage == 1 ? false : true;
	next = endPage < realEnd ? true : false;
	
	// 이전 페이지.
	let prevContent;
	if(!prev){
		prevContent = `<li class="page-item disabled">
				      <span class="page-link">Previous</span>
				    </li>`;
	} else {
		prevContent = `<li class="page-item">
				      <a class="page-link" data-page="${startPage -1}" href="#">Previous</a>
				    </li>`;
	}
		let prevTarget = document.querySelector('nav>ul.pagination');
		prevTarget.insertAdjacentHTML('beforeend', prevContent);
	
	// 페이지 갯수
	let html;
	
	for(let p = startPage; p <= endPage; p++){
		console.log(page)
		if(p == page){
			html = `<li class="page-item active" aria-current="page">
					      <span class="page-link">${p}</span>
					</li>`;
		} else {
			
			html = `<li class="page-item"><a class="page-link" data-page="${p}" href="#">${p}</a></li>`;	
		}
		let target = document.querySelector('nav>ul.pagination');
		target.insertAdjacentHTML('beforeend', html);
	}
	
	// 이후 페이지.
	let netxContent; 
	if(!next){
			netxContent = `<li class="page-item disabled">
					      <span class="page-link">Next</span>
					    </li>`;

		} else {
			netxContent = `<li class="page-item">
					      <a class="page-link" data-page="${endPage + 1}" href="#">Next</a>
					    </li>`;
		}
			let nextTarget = document.querySelector('nav>ul.pagination');
			nextTarget.insertAdjacentHTML('beforeend', netxContent);
	// 링크이벤트.
	pageLink();
}

	
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
				setTimeout(() => {
				// 목록 보여주기
				svc.replyList({bno, page}, successCallback, errorCallback);
				}, 0);


				// 페이징 목록 보여주기
				setTimeout(() => {
					svc.pagingList(bno, pagingCallBack, errorCallback);
				}, 0);
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


