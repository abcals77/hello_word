/**
 * board2.js
 */
const table = new DataTable('#example', {
    ajax: 'replyListDatatable.do?bno=' + bno,
    
	columns: [
        { data: 'REPLY_NO' },
        { data: 'REPLY' },
        { data: 'REPLYER' },
        { data: 'REPLY_DATE' }
    ],
	lengthMenu: [
	    [5, 10, 25, 50, -1],
	    [5, 10, 25, 50, 'All']
	],
	order: [[0, 'desc']]
});
// 추가
function addNewRow() {
	// control을 통해서 db 한건생성.
	console.log('addReply.do?bno=' + bno + '&replyer=' + replyer + '&reply=' + reply.value);
	fetch('addReply.do?bno=' + bno + '&replyer=' + replyer + '&reply=' + reply.value)
	.then(result => result.json()) // json 포맷으로 결과를 변경
	.then(result => {
		console.log(result);
	
	if(result.retCode =="OK"){
    table.row
        .add({
            REPLY_NO: result.retVal.replyNo,
			REPLY: result.retVal.reply,
			REPLYER: result.retVal.replyer,
			REPLY_DATE: result.retVal.replyDate
        })
        .draw(false);
	}
	})
		.catch(err => console.error(err));
}
document.querySelector('#addReply').addEventListener('click',addNewRow);
// document.querySelector('#addRow').addEventListener('click', addNewRow);

// 삭제
table.on('click', 'tbody tr', (e) => {
	console.log(e);
    let classList = e.currentTarget.classList; // 클릭 시 클릭된 tr 클래스 요소를 변수에 넣음 
 
	// classList에 contains('클래스명'), 제거(remove('클래스명')), 추가(add('클래스명'))
    if (classList.contains('selected')) {
        classList.remove('selected'); // 선택되어 있는 클래스를 다시 클릭 시 제거 => 2번 클릭 시 취소되는 기능
    }
    else {
		
        table.rows('.selected').nodes().each((row) => row.classList.remove('selected')); // 이전에 선택환 클래스를 제거
        classList.add('selected'); // 이전에 선택된 클래스가 없으면 새로 추가
    }
});
 
document.querySelector('#delRow')
	.addEventListener('click', function () {
		const selectedRow = table.row('.selected').node();
		let selected = table.row('.selected');
		console.log(selected.data);
		if (!selectedRow) {
		      alert('삭제할 항목을 선택하세요.');
		      return;
		    }
		// 화면에서 삭제.
		let rno = table.row('.selected').node().firstChild.textContent;
		// let rno = document.querySelector('tr.selected').children[0].innerHTML;
		fetch(`removeReply.do?rno=${rno}`)
		.then(result => result.json())
		.then(
			() => alert('삭제완료')
		)
		.catch(err => console.error(err))
		table.row('.selected').remove().draw(false);
		
	});


// end of file