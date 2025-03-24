package com.yedam.board;

public class Board {
	private int boardSerial;
	private int userSerial;
	private String boardTitle;
	private String boardContents;
	private String boardDate;
	private String boardUpDate;
	private int boardStatus;
	
	public Board () {}
	
	public Board(int boardSerial, int userSerial, String boardTitle, String boardContents, String boardDate,
			String boardUpDate) {
		this.boardSerial = boardSerial;
		this.userSerial = userSerial;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.boardDate = boardDate;
		this.boardUpDate = boardUpDate;
	}
	
	public String showMyBoardList() {		
		return boardSerial + " / " + boardTitle + " / " + boardDate;
	}
	
	public String showMyBoard() {
		return "제목 : " + boardTitle + " / 등록일 : " +  boardDate + "\n내용 : " + boardContents ;
	}
		

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

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardUpDate() {
		return boardUpDate;
	}

	public void setBoardUpDate(String boardUpDate) {
		this.boardUpDate = boardUpDate;
	}

	public int getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(int boardStatus) {
		this.boardStatus = boardStatus;
	}

}
