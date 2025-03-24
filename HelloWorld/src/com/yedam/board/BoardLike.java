package com.yedam.board;

public class BoardLike {
	private int boardSerial;
	private int userSerial;
	private String boardLikeDate;

	public BoardLike() {}
	
	public int getBoardSerial() {
		return boardSerial;
	}

	public void setBoardSerial(int boardSerial) {
		this.boardSerial = boardSerial;
	}

	public int getUserSerial() {
		return userSerial;
	}

	public void setUserSerial(int userSerial) {
		this.userSerial = userSerial;
	}

	public String getBoardLikeDate() {
		return boardLikeDate;
	}

	public void setBoardLikeDate(String boardLikeDate) {
		this.boardLikeDate = boardLikeDate;
	}

}
