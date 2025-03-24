package com.yedam.board;

import java.util.List;
import java.util.Scanner;

public class BoardMain {

	private static BoardMain instance = new BoardMain();

	private BoardMain() {
	}

	public static BoardMain getInstance() {
		return instance;
	}

	static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) {
//		System.out.println("id입력>> ");
//		String id = scn.nextLine();
//		System.out.println("password입력>> ");
//		String pw = scn.nextLine();
		String id = "user01";
		String pw = "user01";

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
		MemberJdbc dao = new MemberJdbc();
		return dao.login(id, pw);
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
					int serial = scn.nextInt();
					if (serial == 0) {
						System.out.println("0 입력");
						myPage(id, pw);
						break;
					}
					boolean myContentRun = true;
					myBoardContents(id, pw, serial, member.getUserSerial()); // boardSerial, 입력값

					while (myContentRun) {
						System.out.println("1. 게시글 삭제 / 2. 게시글 수정 / 9. 뒤로가기");
						System.out.print("선택 >> ");
						int boardMenu = 9;
						while (true) {
							try {
								boardMenu = Integer.parseInt(scn.nextLine());
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

	private static void myBoardList(int serial) {
		BoardJdbc dao = new BoardJdbc();
		List<Board> list = dao.myBoardList(serial);
		int seqNo = 1;
		System.out.println("순번 번호   제목         등록일");
		for (Board board : list) {
			if (board != null) {
				System.out.println("<" + seqNo++ + "> " + board.showMyBoardList());
			}
		}
	} // myBoardList

	private static void myBoardContents(String id, String pw, int serial, int userSerial) {
		Member member = login(id, pw);
		BoardJdbc dao = new BoardJdbc();
		Board content = dao.myBoardShow(serial, userSerial);
		if (content != null) {
			System.out.println("=========================================");
			System.out.println(content.showMyBoard());
			System.out.println("=========================================");
		} else {
			System.out.println("없는 일련번호입니다.");
			myBoardList(member.getUserSerial());

		}
	} // myBoardContents

	private static void myBoardDelete(int serial, int userSerial) {
		BoardJdbc dao = new BoardJdbc();
		while (true) {
			System.out.println("\"" + serial + "\" 해당 게시물을 삭제하겠습니까? (Y/N)");
			String deleteYN = scn.nextLine();
			if (deleteYN.equals("Y")) {
				dao.myBoardContentDelete(serial, userSerial);
				System.out.println("일련번호 : " + serial + " 게시물이 삭제되었습니다.");
				break;
			} else if (deleteYN.equals("N")) {
				System.out.println("삭제 취소되었습니다.");
				break;
			}
			System.out.println("Y 또는 N 을 입력해주세요.");
		}
	}
	private static void myBoardUpdate(int serial, int userSerial) {
		System.out.println("수정할 제목을 입력해주세요.");
		System.out.print("입력 >> ");
		String boardTitle = scn.nextLine();
		System.out.println("수정할 내용을 입력해주세요.");
		System.out.print("입력 >> ");
		String boardContent = scn.nextLine();
		
		Board board = new board();
		
		
	}

}
