package com.yedam.board;

import java.util.List;
import java.util.Scanner;

public class BoardMain {
	// 전역변수
	static int returnValues;
	static int stopValues;
	static BoardJdbc boardDao = new BoardJdbc();
	static MemberJdbc memberDao = new MemberJdbc();
	static CommentJdbc commentDao = new CommentJdbc();
	
	static String id;
	static String pw;
	
	private static BoardMain instance = new BoardMain();

	private BoardMain() {
	}

	public static BoardMain getInstance() {
		return instance;
	}

	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("id입력>> ");
		id = scn.nextLine();
		System.out.println("password입력>> ");
		pw = scn.nextLine();
//		id = "user01";
//		pw = "user01";

		while (true) {
			Member member = login(id, pw);
			if (member != null) {
				System.out.println(member.getUserName() + ", 환영합니다.");
				break;
			}
			System.out.println("id와 password를 확인하세요");
			main(args);
		}

		boolean run = true;
		while (run) {
			System.out.println("1. 마이페이지 / 2. 게시판 / 9. 종료");
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
				myPage(id, pw);

				break;
			case 2:
				tbBoard();
				break;

			case 9:
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
		}
		System.out.println("end of prog.");
	} // end of main

	private static Member login(String id, String pw) {
		return memberDao.login(id, pw);
	}

	private static void myPage(String id, String pw) {

		boolean run = true;
		while (run) {
			System.out.println("1. 내 정보 확인 / 2. 내 작성글 확인 / 3. 내 댓글 확인 / 4. 좋아요 게시물 / 5. 회원탈퇴 / 9. 돌아가기");
			System.out.println("선택 >> ");
			int menu = 9;
			while (true) {
				try {
					menu = Integer.parseInt(scn.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.out.println("정수값을 입력하세요.");
				}
			}
			Member member = login(id, pw);

			switch (menu) {
			case 1:
				myInfo(id, pw);
				break;
			case 2:
				myBoardList(member.getUserSerial());
				System.out.println();
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
				        myPage(id, pw);
				        break;  // 반복문 종료
				    } 
					
					myBoardContents(id, pw, serial, member.getUserSerial()); // boardSerial, 입력값
					if(returnValues == 1) {
						continue;
					}
					boolean myContentRun = true;
					while (myContentRun) {
						
						int boardMenu;
						System.out.println("1. 게시글 삭제 / 2. 게시글 수정 / 9. 뒤로가기");						
						while (true) {
							System.out.print("선택 >> ");
							String input =scn.nextLine();
							try {
								boardMenu = Integer.parseInt(input.trim());
								break;
							} catch (NumberFormatException e) {
								System.out.println("정수값을 입력하세요.");
							}
						}

						switch (boardMenu) {
						case 1:
							myBoardDelete(serial, member.getUserSerial());
							myBoardList(member.getUserSerial());
							break;
						case 2:
							myBoardUpdate(serial, member.getUserSerial());
							if(returnValues == 1) {
								myBoardContents(id, pw, serial, member.getUserSerial());
							}
							break;
						case 9:
							myContentRun = false;
							break;
						default:
							System.out.println("다시 입력해주세요.");
						}
					}

					break;
				}
				break;
				// myPage case 2 : 끝
			case 3: // 내 댓글 확인
				myCommentList(member.getUserSerial());
				boolean myCommentRun = true;
				while (myCommentRun) {
					int commentMenu;
					System.out.println("1. 댓글 삭제 / 9. 뒤로가기");						
					while (true) {
						System.out.print("선택 >> ");
						String input =scn.nextLine();
						try {
							commentMenu = Integer.parseInt(input.trim());
							break;
						} catch (NumberFormatException e) {
							System.out.println("정수값을 입력하세요.");
						}
					}
					switch (commentMenu) {
					case 1:
						myCommentDelete(member.getUserSerial());
						myCommentList(member.getUserSerial());
						break;
					case 9:
						myCommentRun = false;
						break;
					default:
						System.out.println("다시 입력해주세요.");
					}
				}
				break;
			case 4: // 내 좋아요 게시물 확인
				break;
			case 5: // 회원탈퇴
				break;
			case 9:
				System.out.println("돌아가기");
				run = false;
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
		}
		System.out.println("end of mypage.");
	}
	// myPage 영역 시작
		// 내 정보
	private static void myInfo(String id, String pw) {
		Member member = login(id, pw);
		System.out.println("아이디	: " + member.getUserId());
		System.out.println("패스워드	: " + member.getUserPw());
		System.out.println("닉네임	: " + member.getUserName());
		System.out.println("이메일	: " + member.getUserEmail());
		System.out.println("가입일	: " + member.getUserDate());
		System.out.println("내 작성글 수 : ");
		System.out.println("내 댓글 수 : ");
	} // myInfo
		// 내 게시물 리스트 보기, 상세보기, 삭제, 수정
	private static void myBoardList(int serial) {
		List<Board> list = boardDao.myBoardList(serial);
		int seqNo = 1;
		System.out.println("순번 번호   제목         등록일");
		for (Board board : list) {
			if (board != null) {
				System.out.println("<" + seqNo++ + "> " + board.showMyBoardList());
			}
		}
	} // myBoardList
	private static void myBoardContents(String id, String pw, int serial, int userSerial) {
		returnValues = 0;
		Member member = login(id, pw);
		BoardJdbc dao = new BoardJdbc();
		Board content = dao.myBoardShow(serial, userSerial);
		if (content != null) {
			System.out.println("================== 상세보기 ==================");
			System.out.println(content.showMyBoard());
			boardComment(serial);
		} else {
			System.out.println("없는 일련번호입니다.");
			System.out.println("=========================================");
			myBoardList(member.getUserSerial());
			returnValues = 1;
			
		}
	} // myBoardContents
	private static void myBoardDelete(int serial, int userSerial) {
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
	private static void myBoardUpdate(int serial, int userSerial) {
		returnValues = 0;
		System.out.println("수정할 제목을 입력해주세요.");
		System.out.print("입력 >> ");
		String boardTitle = scn.nextLine();
		System.out.println("수정할 내용을 입력해주세요.");
		System.out.print("입력 >> ");
		String boardContent = scn.nextLine();
		
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardContents(boardContent);
		board.setBoardSerial(serial);
		board.setUserSerial(userSerial);
		
		if(boardDao.myBoardContentUpdate(board)){
			System.out.println("수정 되었습니다.");
			returnValues = 1;
		}else {
			System.out.println("수정 실패");
		}
		
		
	}
	private static void boardComment(int serial) {
		List<Comment> list = commentDao.boardComment(serial);
		System.out.println("<===== 댓글 영역 =====>");
		for (Comment comment : list) {
			if (comment != null) {
				System.out.println(comment.boardCommentShow());
			}
		}
		System.out.println("<===== 댓글 영역 =====>");
	}	
		// 내 댓글 확인, 삭제
	private static void myCommentList(int serial) {
		List<Comment> list = commentDao.myCommentsList(serial);
		int seqNo = 1;
		System.out.println("==========================================");
		for(Comment comment : list) {
			if(comment != null) {
				System.out.println("<" + seqNo++ + "> " + comment.myCommentListShow());
				System.out.println("==========================================");
			}
		}
	}
	private static void myCommentDelete(int userSerial) {
		System.out.println("삭제를 진행할 댓글 번호를 입력해주세요. (삭제취소 : 0)");
		while(true) {
			System.out.print("선택 >> ");
			String num = scn.nextLine();
			if(commentDao.myCommentDelete(Integer.parseInt(num),userSerial)) {
				System.out.println("댓글번호 : " + num + " 댓글이 삭제되었습니다.");
				break;
			}else if(Integer.parseInt(num) == 0) {
				System.out.println("취소되었습니다.");
				break;
			} else {
				System.out.println("없는 댓글번호입니다. 다시 입력해주세요.");
				continue;
			}
		}
	}
	// myPage 영역 끝
	// 게시판 영역
	private static void tbBoard() {
		List<Board> list = boardDao.tbBoard();
		int seqNo = 1;
		System.out.println("순번     번호             제목               등록일");
		for (Board board : list) {
			if (board != null) {
				System.out.println("<" + seqNo++ + "> 게시물 번호 : " + board.getBoardSerial() + " / 제목 : " + board.getBoardTitle() + " / 등록일 : " + board.getBoardDate());  
			}
		}
		
		boolean run = true;
		while(run) {
			System.out.println("1. 게시물 상세보기 / 9. 뒤로가기");
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
				        break;  // 반복문 종료
				    } 
					if(returnValues == 1) {
						continue;
					}
					boardContents(serial);
					
					boolean contentRun = true;
					while (contentRun) {
						Member member = login(id, pw); 
						int contentMenu;
						System.out.println("1. 댓글쓰기 / 2. 좋아요 / 0. 뒤로가기");						
						while (true) {
							System.out.print("선택 >> ");
							String input =scn.nextLine();
							try {
								contentMenu = Integer.parseInt(input.trim());
								break;
							} catch (NumberFormatException e) {
								System.out.println("정수값을 입력하세요.");
							}
						}

						switch (contentMenu) {
						case 1:
							addComment(serial, member.getUserSerial());
							boardContents(serial);
							break;
						case 2:
							boardContentLike(serial, member.getUserSerial());
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
			case 9:
				System.out.println("게시판을 종료합니다.");
				run = false;
				return;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
			
		}
		
	}
	private static void boardContents(int serial) {
		returnValues = 0;
		BoardJdbc dao = new BoardJdbc();
		Board content = dao.boardContent(serial);
		if (content != null) {
			System.out.println("================== 상세보기 ==================");
			System.out.println(content.boardShow());
			boardComment(serial);
		} else {
			System.out.println("없는 일련번호입니다.");
			System.out.println("=========================================");
			tbBoard();
			returnValues = 1;
			
		}
	} // myBoardContents
	
	private static void addComment(int boardSerial, int userSerial) {
		System.out.print("댓글 내용 입력(공백 시 취소) >> ");
		String content = scn.nextLine();
		if (content.isBlank()) {
			System.out.println("취소 되었습니다. ");
			return; // 메소드 종료.
		}
		Comment comment = new Comment(boardSerial, content, userSerial);
		if (commentDao.boardCommentInsert(comment)) {
			System.out.println("댓글 등록이 완료되었습니다..");
		} else {
			System.out.println("등록이 실패하였습니다.");
		}
		
		
	}
	
	private static void boardContentLike(int boardSerial, int userSerial) {
		while (true) {
			System.out.println("\"" + boardSerial + "\" 해당 게시물을 '좋아요'하겠습니까? (Y/N)");
			String deleteYN = scn.nextLine();
			if (deleteYN.equals("Y") || deleteYN.equals("y")) {
				boardDao.boardLike(boardSerial, userSerial);
				System.out.println("게시물 번호 : " + boardSerial + " '좋아요'하였습니다.");
				break;
			} else if (deleteYN.equals("N") || deleteYN.equals("n")) {
				System.out.println("취소되었습니다.");
				break;
			}
			System.out.println("Y 또는 N 을 입력해주세요.");
		}
	}
}
