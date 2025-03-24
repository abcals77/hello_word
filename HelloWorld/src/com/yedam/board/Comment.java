package com.yedam.board;

public class Comment {
	private int commentSerial;
	private int boardSerial;
	private String commentContents;
	private int user_serial;
	private String commentDate;
	private int commentStatus;
	
	public Comment() {}

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

	public int getUser_serial() {
		return user_serial;
	}

	public void setUser_serial(int user_serial) {
		this.user_serial = user_serial;
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
