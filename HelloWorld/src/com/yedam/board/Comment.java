package com.yedam.board;

public class Comment {
	private int commentSerial;
	private int boardSerial;
	private String commentContents;
	private int userSerial;
	private String commentDate;
	private int commentStatus;

	public Comment() {
	}

	public Comment(int commentSerial, int boardSerial, String commentContents, int userSerial, String commentDate,
			int commentStatus) {
		this.commentSerial = commentSerial;
		this.boardSerial = boardSerial;
		this.commentContents = commentContents;
		this.userSerial = userSerial;
		this.commentDate = commentDate;
		this.commentStatus = commentStatus;
	}
	public Comment(int boardSerial, String commentContents, int userSerial) {
		this.boardSerial = boardSerial;
		this.commentContents = commentContents;
		this.userSerial = userSerial;
	}

	public String myCommentListShow() {
		return "댓글 번호 : " + commentSerial + " / 게시물 번호 : "+ boardSerial + " / 등록일 : " + commentDate + "\n내용 : " + commentContents;
	}
	public String boardCommentShow() {
		return "댓글 번호 : " + commentSerial + " / 등록일 : " + commentDate + "\n내용 : " + commentContents;
	}
	
	
	public int getCommentSerial() {
		return commentSerial;
	}

	public void setCommentSerial(int commentSerial) {
		this.commentSerial = commentSerial;
	}

	public int getBoardSerial() {
		return boardSerial;
	}

	public void setBoardSerial(int boardSerial) {
		this.boardSerial = boardSerial;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public int getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(int userSerial) {
		this.userSerial = userSerial;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public int getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(int commentStatus) {
		this.commentStatus = commentStatus;
	}

}
