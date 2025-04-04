/**
 * array
 * push, unshift 추가
 * pop, shift 제거.
 * splice 추가, 수정, 삭제
 * forEach()
 */

const ary = [];
// push, unshift
ary.push('홍길동'); // 마지막위치.
ary.push('김길동'); // 
ary.unshift('최길동'); // 가장 앞

// ary.pop(); // 마지막 삭제
// ary.shift(); // 가장 앞 삭제

// ary.splice(0, 1, '박길동','황길동'); // 첫번째, 크기(값 수), 대체값 => 첫번째 1개 값과 2개를 대체
// ary.splice(0, 0, '박길동','황길동'); // 첫번째에 2개 추가
// ary.splice(0, 1); // 첫번째 1개 값을 삭제

ary.forEach(function(item, idx, ary) {
	console.log(`item=> ${item}, index=> ${idx}, array=> ${ary}`);
});

// 함수.
function addElement(elem = "noElem"){
	ary.push(elem);
}
function deleteElement(e){
	// alert('삭제버튼클릭됨');
	console.log(e.target.parentElement);
	e.target.parentElement.remove();
}

// 추가버튼에 클릭이벤트.
document.querySelector('button#addBtn').addEventListener('click', function(){
	// input의 값.
	let val = document.querySelector('input#userInput').value;
	addElement(val); // ary배열에 추가
	// 호출
	let html = '';
	ary.forEach(function(item, idx, ary){
		html += '<li>' + item + '<button onclick="deleteElement(event)">삭제</button></li>';
	});
	// ul 태그의 영역
	document.querySelector('ul#list').innerHTML = html;
});

