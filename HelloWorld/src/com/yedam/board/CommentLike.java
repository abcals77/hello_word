package com.yedam.board;

public class CommentLike {
	private int commentSerial;
	private int boardSerial;
	private String commentLikeDate;
	
	public CommentLike() {}

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

	public String getCommentLikeDate() {
		return commentLikeDate;
	}

	public void setCommentLikeDate(String commentLikeDate) {
		this.commentLikeDate = commentLikeDate;
	}

}
