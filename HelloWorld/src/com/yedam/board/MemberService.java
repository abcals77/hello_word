package com.yedam.board;

import java.util.List;
import java.util.Scanner;
import com.yedam.board.MemberJdbc;
import com.yedam.board.Member;

public class MemberService {
	private static Scanner scn = new Scanner(System.in);
    private static MemberJdbc memberDao = new MemberJdbc();
    private static BoardJdbc boardDao = new BoardJdbc();
    private static CommentJdbc commentDao = new CommentJdbc();

    // 회원가입
    public static void sign() {
		System.out.println("==================");
		System.out.println("회원등록");
		System.out.println("==================");

		boolean register = false;

		while(!register){
				System.out.println("회원가입을 하시겠습니까?\nY:진행 N:취소");
				System.out.print("입력 >> ");
				String yn = scn.nextLine();
	
			if(yn.equals("y") || yn.equals("Y")){ // 대소 구분 X
				System.out.println("==================");
				System.out.println("회원가입이 진행됩니다.");
				System.out.println("==================");
			}else if(yn.equals("n") || yn.equals("N")){
				System.out.println("==================");
				System.out.println("회원가입을 종료합니다.");
				System.out.println("==================");
				return;
			}else{
				System.out.println("==================");
				System.out.println("Y 또는 N만 입력해주세요.");
				System.out.println("회원가입을 종료합니다.");
				System.out.println("==================");
				return;
			}
			Member member = new Member();
	
			while(true){
				
				String userId = "";
				boolean idCk = true;
				while(idCk) {
					List<Member> list = memberDao.memberList();
					System.out.print("ID : ");
					userId = scn.nextLine();
					if(userId.isBlank()) {
						System.out.println("아이디를 입력해주세요.");
						continue;
					}
					boolean exit = false;
					for (Member memberCk : list) {
						if (memberCk.getUserId().equals(userId)) {
							System.out.println("중복된 아이디입니다. 다시 입력해주세요.");
							exit = true;
						}
					}
					if(!exit) {
						idCk = false;
					}
					
				}

				String userPw = "";
				while(true){
					System.out.print("PW : ");
					userPw = scn.nextLine();
					System.out.print("PW 확인 : ");
					String passwordConfirm = scn.nextLine();
					if(userPw.isBlank()) {
						System.out.println("비밀번호를 입력해주세요.");
						continue;
					}
					if(userPw.equals(passwordConfirm)){
						break;
					}else{
						System.out.println("==================");
						System.out.println("패스워드를 확인해주세요");
						System.out.println("==================");
					}
				}
				String name = "";
				while(true) {
					System.out.print("닉네임 : ");
					name = scn.nextLine();
					if(name.isBlank()) {
						System.out.println("닉네임을 입력해주세요.");
					}else {
						break;
					}
				}
				String email = "";
				while(true) {
					System.out.print("이메일을 입력해주세요 : ");
					email = scn.nextLine();
					if(email.isBlank()) {
						System.out.println("이메일을 입력해주세요.");
					}else {
						break;
					}
				}
				
				member.setUserId(userId);
				member.setUserPw(userPw);
				member.setUserName(name);
				member.setUserEmail(email);
				
				if(memberDao.memberSignUp(member)){
					System.out.println("==================");
					System.out.println(member.getUserName()+"님 회원가입 되었습니다.");
					System.out.println("로그인 페이지에서 로그인을 진행해주세요.");
					System.out.println("==================");
					return;
				}else {
					System.out.println("회원가입 실패");
				}
				break;
			}
		}
	}
	
    // 로그인
 	public static Member login(String id, String pw) {
 		return memberDao.login(id, pw);
 	}

 	// 회원탈퇴
 	public static void memberDelete(String userId, String userPw) {
		System.out.println("==================");
		System.out.println("회원탈퇴");
		System.out.println("==================");

		boolean register = false;

		while (!register) {
			System.out.println("회원탈퇴를 진행 하시겠습니까?\nY:진행 N:취소");
			System.out.print("입력 >> ");
			String yn = scn.nextLine();

			if (yn.equalsIgnoreCase("y")) { // 대소 구분 X
				System.out.println("==================");
				System.out.println("회원탈퇴가 진행됩니다.");
				System.out.println("==================");
			} else if (yn.equalsIgnoreCase("n")) {
				System.out.println("==================");
				System.out.println("회원탈퇴를 종료합니다.");
				System.out.println("==================");
				return;
			} else {
				System.out.println("==================");
				System.out.println("Y 또는 N만 입력해주세요.");
				System.out.println("==================");
			}
			System.out.println("아이디를 입력해주세요.");
			System.out.print("입력 >> ");
			String uid = scn.nextLine();
			System.out.println("비밀번호를 입력해주세요.");
			System.out.print("입력 >> ");
			String upw = scn.nextLine();
			if (uid.isBlank() || upw.isBlank()) {
				System.out.println("========================");
				System.out.println("아이디 또는 비밀번호가 공백입니다.");
				System.out.println("회원탈퇴를 취소합니다.");
				System.out.println("========================");
				return;
			}
			if (uid.equals(userId) && upw.equals(userPw)) {
				if (memberDao.memberDelete(uid, upw)) {
					System.out.println("회원탈퇴 되었습니다.");
					BoardMain.id = "";
					BoardMain.pw = "";
					BoardMain.main(null);
					return;
				}
			} else {
				System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
				System.out.println("회원탈퇴를 취소합니다.");
				return;
			}

		}

	}
    
 	// 내 정보 보기
 	public static void myInfo(String id, String pw) {
 		Member member = MemberService.login(id, pw);
 		int myBoardCount = 0;
 		int myCommentCount = 0;
 		List<Board> list = boardDao.myBoardList(member.getUserSerial());
 		for (Board board : list) {
 			if (board != null) {
 				myBoardCount++;
 			}
 		}
 		List<Comment> clist = commentDao.myCommentsList(member.getUserSerial());
 		for (Comment comment : clist) {
 			if (comment != null) {
 				myCommentCount++;
 			}
 		}
 		System.out.println("아이디	: " + member.getUserId());
 		System.out.println("패스워드	: " + member.getUserPw());
 		System.out.println("닉네임	: " + member.getUserName());
 		System.out.println("이메일	: " + member.getUserEmail());
 		System.out.println("가입일	: " + member.getUserDate());
 		System.out.println("내 작성글 수 : " + myBoardCount);
 		System.out.println("내 댓글 수 : " + myCommentCount);
 	} // myInfo


 	// 마이페이지 영역
	public static boolean myPage(String id, String pw) {
		boolean run = true;
		while (run) {
			System.out.println("======================================");
			System.out.println("1. 내 정보 확인 / 2. 내 작성글 확인 / 3. 내 댓글 확인 / 4. 좋아요 게시물 / 5. 로그아웃 / 6. 회원탈퇴 / 9. 돌아가기");
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
			Member member = MemberService.login(id, pw);

			switch (menu) {
			case 1:
				MemberService.myInfo(id, pw);
				break;
			case 2:
				BoardService.myBoardList(member.getUserSerial());
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
						break; // 반복문 종료
					}

					BoardService.myBoardContents(id, pw, serial, member.getUserSerial()); // boardSerial, 입력값
					if (BoardMain.returnValues == 1) {
						continue;
					}
					boolean myContentRun = true;
					while (myContentRun) {

						int boardMenu;
						System.out.println("======================================");
						System.out.println("1. 게시글 삭제 / 2. 게시글 수정 / 3. 댓글쓰기 / 9. 뒤로가기");
						while (true) {
							System.out.print("선택 >> ");
							String input = scn.nextLine();
							try {
								boardMenu = Integer.parseInt(input.trim());
								break;
							} catch (NumberFormatException e) {
								System.out.println("정수값을 입력하세요.");
							}
						}
						
						switch (boardMenu) {
						case 1:
							BoardService.myBoardDelete(serial, member.getUserSerial());
							myContentRun = false;  
							break;
						case 2:
							BoardService.myBoardUpdate(serial, member.getUserSerial());
							if (BoardMain.returnValues == 1) {
								BoardService.myBoardContents(id, pw, serial, member.getUserSerial());
							}
							break;
						case 3:
							CommentService.addComment(serial, member.getUserSerial());
							BoardService.boardContents(serial);
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
			case 3: // 내 댓글 확인
				CommentService.myCommentList(member.getUserSerial());
				boolean myCommentRun = true;
				while (myCommentRun) {
					int commentMenu;
					System.out.println("1. 댓글 삭제 / 9. 뒤로가기");
					while (true) {
						System.out.print("선택 >> ");
						String input = scn.nextLine();
						try {
							commentMenu = Integer.parseInt(input.trim());
							break;
						} catch (NumberFormatException e) {
							System.out.println("정수값을 입력하세요.");
						}
					}
					switch (commentMenu) {
					case 1:
						CommentService.myCommentDelete(member.getUserSerial());
						CommentService.myCommentList(member.getUserSerial());
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
				BoardService.myBoardLike(member.getUserSerial());
				BoardMain.returnValues = 0;
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
						break; // 반복문 종료
					}
					if (BoardMain.returnValues == 1) {
						continue;
					}
					BoardService.boardContents(serial);

					boolean contentRun = true;
					while (contentRun) {
						int contentMenu;
						System.out.println("1. 댓글쓰기 / 0. 뒤로가기");
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
							BoardService.boardContents(serial);
							break;
						case 0:
							contentRun = false;
							break;
						default:
							System.out.println("다시 입력해주세요.");
						}
					}

					break;
				}

				break;
			case 5: // 로그아웃
				return true; // 로그아웃 신호 main에 전달
			case 6: // 회원탈퇴
				MemberService.memberDelete(member.getUserId(), member.getUserPw());
				break;
			case 9:
				System.out.println("돌아가기");
				run = false;
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}

		}
		System.out.println("마이페이지 종료");
		return false; // 로그아웃 안함
	}

}
