package com.yedam.board;

import java.util.List;
import java.util.Scanner;

public class BoardService {
	private static Scanner scn = new Scanner(System.in);
    private static BoardJdbc boardDao = new BoardJdbc();

    // 게시판 - 게시물 좋아요 누르기
    public static void boardContentLike(int boardSerial, int userSerial) {
		while (true) {
			System.out.println("\"" + boardSerial + "\" 해당 게시물을 '좋아요'하겠습니까? (Y/N)");
			String deleteYN = scn.nextLine();
			if (deleteYN.equals("Y") || deleteYN.equals("y")) {
				if (boardDao.boardLike(boardSerial, userSerial)) {
					System.out.println("게시물 번호 : " + boardSerial + " '좋아요'하였습니다.");
					break;
				} else {
					System.out.println("이미 '좋아요'하였습니다.");
					break;
				}

			} else if (deleteYN.equals("N") || deleteYN.equals("n")) {
				System.out.println("취소되었습니다.");
				break;
			}
			System.out.println("Y 또는 N 을 입력해주세요.");
		}
	}
    
    // 게시판 - 글쓰기
	public static void addBoard(int userSerial) {
		System.out.println("제목을 입력해주세요.");
		System.out.print("입력 >> ");
		String boardTitle = scn.nextLine();
		System.out.println("내용을 입력해주세요.");
		System.out.print("입력 >> ");
		String boardContent = scn.nextLine();

		if (boardTitle.isBlank() || boardContent.isBlank()) {
			System.out.println("========================");
			System.out.println("제목 또는 내용에 공백을 넣을 수는 없습니다.");
			System.out.println("등록을 종료합니다.");
			System.out.println("========================");
			return;
		}

		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContents(boardContent);
		board.setUserSerial(userSerial);

		if (boardDao.boardInsert(board)) {
			System.out.println("등록 되었습니다.");
		} else {
			System.out.println("등록 실패");
		}
	}

	// 마이페이지 - 내 게시물 수정
	public static void myBoardUpdate(int serial, int userSerial) {
		BoardMain.returnValues = 0;
		System.out.println("수정할 제목을 입력해주세요.");
		System.out.print("입력 >> ");
		String boardTitle = scn.nextLine();
		System.out.println("수정할 내용을 입력해주세요.");
		System.out.print("입력 >> ");
		String boardContent = scn.nextLine();

		if (boardTitle.isBlank() || boardContent.isBlank()) {
			System.out.println("========================");
			System.out.println("제목 또는 내용에 공백을 넣을 수는 없습니다.");
			System.out.println("수정을 종료합니다.");
			System.out.println("========================");
			BoardMain.returnValues = 1;
			return;
		}
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContents(boardContent);
		board.setBoardSerial(serial);
		board.setUserSerial(userSerial);

		if (boardDao.myBoardContentUpdate(board)) {
			System.out.println("수정 되었습니다.");
			BoardMain.returnValues = 1;
		} else {
			System.out.println("수정 실패");
		}

	}

	// 마이페이지 - 내 게시물 삭제
	public static void myBoardDelete(int serial, int userSerial) {
		while (true) {
			System.out.println("\"" + serial + "\" 해당 게시물을 삭제하겠습니까? (Y/N)");
			String deleteYN = scn.nextLine();
			if (deleteYN.equals("Y") || deleteYN.equals("y")) {
				boardDao.myBoardContentDelete(serial, userSerial);
				System.out.println("일련번호 : " + serial + " 게시물이 삭제되었습니다.");
				break;
			} else if (deleteYN.equals("N") || deleteYN.equals("n")) {
				System.out.println("삭제 취소되었습니다.");
				break;
			}
			System.out.println("Y 또는 N 을 입력해주세요.");
		}
	}
	
	// 마이페이지 - 내 게시물 목록
	public static void myBoardList(int serial) {
		List<Board> list = boardDao.myBoardList(serial);
		int seqNo = 1;
		System.out.println("순번 번호        제목             등록일");
		for (Board board : list) {
			if (board != null) {
				int likeCount = boardDao.boardLikeCount(board.getBoardSerial());
				System.out.println("<" + seqNo++ + "> " + board.showMyBoardList() + " | 좋아요 수 : " + likeCount);
			}
		}
	} // myBoardList
	
	// 마이페이지 - 좋아요 게시물 목록
	public static void myBoardLike(int serial) {
		List<Board> list = boardDao.myLikeBoard(serial);
		int seqNo = 1;
		System.out.println("순번 번호   제목         등록일");
		for (Board board : list) {
			if (board != null) {
				System.out.println("<" + seqNo++ + "> " + board.showMyBoardList());
			}
		}
	}
	
	// 게시물 - 상세보기
	public static void myBoardContents(String id, String pw, int serial, int userSerial) {
		BoardMain.returnValues = 0;
		Member member = MemberService.login(id, pw);
		BoardJdbc dao = new BoardJdbc();
		Board content = dao.myBoardShow(serial, userSerial);
		if (content != null) {
			System.out.println("================== 상세보기 ==================");
			System.out.println(content.showMyBoard());
			CommentService.boardComment(serial);
		} else {
			System.out.println("없는 일련번호입니다.");
			System.out.println("=========================================");
			BoardService.myBoardList(member.getUserSerial());
			BoardMain.returnValues = 1;

		}
	}
	
	// 게시판 영역
	public static void tbBoard() {
		BoardMain.stopValues = 0;
		Member member = MemberService.login(BoardMain.id, BoardMain.pw);
		List<Board> list = boardDao.tbBoard();
		int seqNo = 1;
		System.out.println("순번     번호             제목                  등록일");
		for (Board board : list) {
			if (board != null) {
				int likeCount = boardDao.boardLikeCount(board.getBoardSerial());
				System.out.println("<" + seqNo++ + "> 게시물 번호 : " + board.getBoardSerial() + " / 제목 : "
						+ board.getBoardTitle() + " / 등록일 : " + board.getBoardDate() + " / 좋아요 수 : " + likeCount);
			}
		}

		boolean run = true;
		while (run) {
			if (BoardMain.stopValues == 1) {
				break;
			}
			System.out.println("======================================");
			System.out.println("1. 게시물 상세보기 / 2. 글쓰기 / 9. 뒤로가기");
			System.out.print("선택 >> ");
			int menu = 9;
			while (true) {
				try {
					menu = Integer.parseInt(scn.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("정수값을 입력하세요.");
				}
			}
			switch (menu) {
			case 1:
				while (true) {
					System.out.println("상세보기 게시물을 선택해주세요.(0입력 시 뒤로가기)");
					System.out.print("일련번호 입력 >>");
					int serial;
					try {
						serial = Integer.parseInt(scn.nextLine().trim());
					} catch (NumberFormatException e) {
						System.out.println("올바른 일련번호를 입력해주세요.");
						continue; // 다시 반복문 시작
					}
					if (serial == 0) {
						System.out.println("뒤로 돌아갑니다.");
						tbBoard();
						break; // 반복문 종료
					}
					if (BoardMain.returnValues == 1) {
						BoardMain.returnValues = 0;
						continue;
					}
					boardContents(serial);

					boolean contentRun = true;
					while (contentRun) {

						int contentMenu;
						System.out.println("======================================");
						System.out.println("1. 댓글쓰기 / 2. 좋아요 / 0. 뒤로가기");
						while (true) {
							System.out.print("선택 >> ");
							String input = scn.nextLine();
							try {
								contentMenu = Integer.parseInt(input.trim());
								break;
							} catch (NumberFormatException e) {
								System.out.println("정수값을 입력하세요.");
							}
						}

						switch (contentMenu) {
						case 1:
							CommentService.addComment(serial, member.getUserSerial());
							boardContents(serial);
							break;
						case 2:
							BoardService.boardContentLike(serial, member.getUserSerial());
							boardContents(serial);
							break;
						case 0:
							contentRun = false;
							tbBoard();
							break;
						default:
							System.out.println("다시 입력해주세요.");
						}
					}

					break;
				}
				break;
			case 2:
				BoardService.addBoard(member.getUserSerial());
				tbBoard();
				break;
			case 9:
				System.out.println("게시판을 종료합니다.");
				run = false;
				BoardMain.stopValues = 1;
				return;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}

		}

	}
	
	// 게시물 상세보기
	public static void boardContents(int serial) {
		Board content = boardDao.boardContent(serial);
		String name = boardDao.boardUserName(serial);
		if (content != null) {
			System.out.println("================== 상세보기 ==================");
			System.out.println(content.boardShowU() + name + content.boardShowD());
			CommentService.boardComment(serial);
		} else {
			System.out.println("없는 일련번호입니다.");
			System.out.println("=========================================");
			tbBoard();
			BoardMain.returnValues = 1;

		}
	} // myBoardContents
	
	
	
}
