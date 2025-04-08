/**
 * boardService.js
 */

const svc = {
	name: "홍길동",
	// 목록
	replyList: function(search = {bno, page}, successCallback, errorCall){
		//fetch(`replyList.do?bno=${search.bno}&page=${search.page}`)
		fetch('replyList.do?bno=' + search.bno + '&page=' + search.page)
		.then(result => result.json())
		.then(successCallback)
		.catch(errorCall)
	},
	// 삭제.
	removeReply(rno, successCallback, errorCall){
		fetch(`removeReply.do?rno=${rno}`)
		.then(result => result.json())
		.then(successCallback)
		.catch(errorCall)
	},
	// 추가
	addReply(rvo = {bno, reply, replyer}, successCallback, errorCall){
		fetch('addReply.do?bno=' + rvo.bno + '&reply=' + rvo.reply + '&replyer=' + rvo.replyer)
		.then(result => result.json())
		.then(successCallback)
		.catch(errorCall)
	},
	// 댓글 페이지 계산.
	pagingList(bno = 152, successCallback, errorCall){
			fetch('replyCount.do?bno=' + bno)
			.then(result => result.json())
			.then(successCallback)
			.catch(errorCall)
		}
	
	
}