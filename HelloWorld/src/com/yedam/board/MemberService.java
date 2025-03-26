package com.yedam.board;

import java.util.List;
import java.util.Scanner;
import com.yedam.board.MemberJdbc;
import com.yedam.board.Member;

public class MemberService {
	private static Scanner scn = new Scanner(System.in);
    private static MemberJdbc memberDao = new MemberJdbc();
    private static Member member;
    
    static void sign() {
		System.out.println("==================");
		System.out.println("회원등록");
		System.out.println("==================");

		boolean register = false;

		while(!register){
				System.out.println("회원가입을 하시겠습니까?\nY:진행 N:취소");
				System.out.print("입력 >> ");
				String yn = scn.nextLine();
	
			if(yn.equalsIgnoreCase("y")){ // 대소 구분 X
				System.out.println("==================");
				System.out.println("회원가입이 진행됩니다.");
				System.out.println("==================");
			}else if(yn.equalsIgnoreCase("n")){
				System.out.println("==================");
				System.out.println("회원가입을 종료합니다.");
				System.out.println("==================");
				return;
			}else{
				System.out.println("==================");
				System.out.println("Y 또는 N만 입력해주세요.");
				System.out.println("==================");
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
	
	
}
