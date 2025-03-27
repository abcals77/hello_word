package com.yedam.board;

import java.util.Scanner;

public class BoardMain {
	// 전역변수
	static int returnValues = 0;
	static int stopValues = 0;
	static BoardJdbc boardDao = new BoardJdbc();
	static MemberJdbc memberDao = new MemberJdbc();
	static CommentJdbc commentDao = new CommentJdbc();
	static String id;
	static String pw;
	static Scanner scn = new Scanner(System.in);
	private static BoardMain instance = new BoardMain();
	private BoardMain() {}
	public static BoardMain getInstance() {
		return instance;
	}

	
	public void start() {
		boolean start = true;

		while (start) {
			System.out.println("======================================");
			System.out.println("1. 회원가입 / 2. 로그인 / 9. 종료");
			System.out.print("선택 >> ");
			int smenu = inputInt();

			switch (smenu) {
			case 1:
				MemberService.sign();
				break;
			case 2:
				System.out.println("id입력>> ");
				id = scn.nextLine();
				System.out.println("password입력>> ");
				pw = scn.nextLine();

				Member member = MemberService.login(id, pw);
				if (member == null) {
					System.out.println("id와 password를 확인하세요");
					continue; // 로그인 실패하면 다시 로그인 메뉴로
				}
				System.out.println(member.getUserName() + ", 환영합니다.");

				// 로그인 후 메뉴 실행
				boolean isLoggedIn = true;
				while (isLoggedIn) {
					System.out.println("======================================");
					System.out.println("1. 마이페이지 / 2. 게시판 / 9. 로그아웃");
					System.out.print("선택 >> ");
					int menu = inputInt();

					switch (menu) {
					case 1:
						if (MemberService.myPage(id, pw)) { // 로그아웃 했으면 true 반환
							System.out.println("로그아웃 되었습니다.");
							id = "";
							pw = "";
							isLoggedIn = false; // main에서도 로그아웃 상태 처리
						}
						break;
					case 2:
						BoardService.tbBoard();
						break;
					case 9:
						System.out.println(member.getUserName() + "님이 로그아웃 하였습니다.");
						id = "";
						pw = "";
						isLoggedIn = false; // 로그인 상태 종료 (로그아웃)
						break;
					default:
						System.out.println("메뉴를 다시 선택하세요");
					}
				}
				break; // 로그인 메뉴(메인메뉴)로 돌아감

			case 9:
				System.out.println("프로그램을 종료합니다.");
				start = false;
				break;

			default:
				System.out.println("올바른 값을 입력해주세요.");
			}
		}
		System.out.println("end of prog.");
	}

	private int inputInt() {
		while (true) {
			try {
				return Integer.parseInt(scn.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("정수값을 입력하세요.");
			}
		}
	}

	public static void main(String[] args) {
		BoardMain.getInstance().start();
	}


	



	
}
