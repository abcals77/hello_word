package com.yedam.variable;

import java.util.Scanner;

// 추가, 수정, 삭제, 목록 출력

public class VarExe7 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		boolean run = true;
		// Member 값을 저장
		Member[] storage = new Member[100]; // {null, null, ... null}
		storage[0] = new Member("홍길동", 83);
		storage[1] = new Member("김민혁", 86);
		storage[2] = new Member("한수아", 90);
		storage[3] = new Member("김해랑", 70);
		
		while(run) {
			System.out.println("1.등록 2.수정 3.삭제 4.출력 5.평균 6.종료");
			System.out.print("선택>>");
			int menu = Integer.parseInt(scn.nextLine()); // 1 엔터. 
			
			switch(menu) {
			case 1: // 등록.
				System.out.print("이름입력>>");
				String name = scn.nextLine();
				System.out.print("점수입력>>");
				int score = Integer.parseInt(scn.nextLine());
				Member member = new Member(); // 인스턴스 생성
//				member.name = name;
//				member.score = score;
				member.setMember(name, score);
//				 빈공간에 값을 할당.
				for(int i = 0; i < storage.length; i++) {
					if(storage[i] == null) { // 빈공간에 넣기 위해 null 확인
						storage[i] = member;
						break; // for 반복문 종료
					}
				}
				break; // case 1의 종료
			case 2:
				System.out.print("수정할 이름 입력>>");
				name = scn.nextLine();
				boolean isExist = false;
				for(int i = 0; i < storage.length; i++) {
					if(storage[i] != null && storage[i].getName().equals(name)) { // if 참 값이 있으면 해당 값이 반환되고 break
						System.out.print(storage[i].getName() + "의 수정할 점수를 입력해주세요>>");
						score = Integer.parseInt(scn.nextLine());
						storage[i].setScore(score);
						System.out.println("수정되었습니다.");
						isExist = true;
						break;
					}
				}
				
				if(!isExist) { // 기존 값은 false => 앞에 if문에 참 값이 없을 경우  true 값을 들고 오지 못해서 해당 if문에서 false는 true가 되고 if문 작동
					System.out.println("없는 이름입니다.");
				}
				
				break; // if에 참 값이 없으면 for문 1회 반복 후 나온 다음 break
			case 3: // 삭제. 이름입력 조회 후 => null 대입.
				System.out.print("삭제할 이름 입력>>");
				name = scn.nextLine();
				boolean isExistD = false;
				for(int i = 0; i < storage.length; i++) {
					if(storage[i] != null && storage[i].getName().equals(name)) {
						storage[i] = null; // 삭제.
						System.out.println("삭제되었습니다.");
						isExistD = true;
						break;
					}
				if(!isExistD) { // if
					System.out.println("없는 이름입니다.");
					}	
				}
				break;
			case 4: // 목록 출력.
				for(int i = 0; i < storage.length; i++) {
					if(storage[i] != null) {
						System.out.println(storage[i].getName() + " " + storage[i].getScore());
					}
				}
				break;
			case 5:
				int sum = 0;
				int count = 0;
				for(int i = 0; i < storage.length; i++) {			
					if(storage[i] != null) {
					sum += storage[i].getScore();								
					count++;
					}
				}
//				System.out.println(sum);
				double avg = sum * 1.0 / count; 
//				System.out.println(avg);
//				System.out.println(count);
				System.out.println("평균점수는 " + avg + "입니다.");
				break;
			case 6:
				run = false;
			}
			
		}
		System.out.println("end of prog");
		scn.close();
	} // end of main().
	
}
