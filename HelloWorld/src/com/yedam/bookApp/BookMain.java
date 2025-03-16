package com.yedam.bookApp;
/*
 * 등록, 수정, 삭제, 목록
 */

import java.util.Scanner;

public class BookMain {	
	// 2. 정적필드. 할당.
	private static BookMain instance = new BookMain();
	//  싱글톤 1. 생성자 private 선언
	private BookMain() {
		
	}
	// 3. getInstance() 제공
	public static BookMain getInstance() {
		return instance;
	}
	
	 Scanner scn = new Scanner(System.in);
	 Book[] bookStore = new Book[100];

	public void init() {
		bookStore[0] = new Book("이것이자바다", "신용권", "한빛출", 20000, 1);
		bookStore[1] = new Book("스트립트기초", "박기초", "우리출", 26000, 2);
		bookStore[2] = new Book("HTML,CSS", "김하늘", "가람출", 25000, 3);
		bookStore[3] = new Book("자바칩", "김자바", "한빛출", 25000, 3);
		bookStore[4] = new Book("C++", "박씨", "우리출", 25000, 3);
		bookStore[5] = new Book("JS칩", "김제이", "가람출", 25000, 3);
	}
	// static : 해당 클래스의 인스턴스를 만들지 않아도 해당 클래스가 실행되면 자동으로 사용가능
	User[] bookUser = new User[3];
	
	public void bookUser() {
		bookUser[0] = new User("aaa","aaa","aaa");
		bookUser[1] = new User("bbb","bbb","bbb");
		bookUser[2] = new User("ccc","ccc","ccc");
	}
	public void login(String userId, String password) {
		
		boolean isLogin = false;
		
		for(int i = 0; i < bookUser.length; i++) {
			if(bookUser[i].getUserId().equals(userId) 
					&& bookUser[i].getPassword().equals(password)) {
				isLogin = true;
			}
		}
		if(isLogin) {
			System.out.println("ID : " + userId + "님이 로그인했습니다.");
			main(null);
		}else {
			System.out.println("로그인 정보가 일치하지 않습니다.");
		}
	}
	
		
	
	
	// 순번생성.
	public int getSequnceNo() {
		int max = 0;
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getOrderNo() > max) {
				max = bookStore[i].getOrderNo();
			}
		}
		return max + 1; // 현재 마지막번호 + 1
	}

	// 등록
	// 1. 이미 존재하는 제목은 입력불가.
	public  void add() {
		System.out.print("제목입력>> ");
		String title = scn.nextLine();
		if (title.isBlank()) {
			System.out.println("책 제목을 입력해주세요.");
			return; // 메소드 종료.
		} else {
			for (int i = 0; i < bookStore.length; i++) {
				if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
					System.out.println("중복된 책 제목입니다.");
					return;
				}
			}
		}
//		if(searchBook(title) != null) {
//			System.out.println("이미 등록된 제목입니다.");
//			return;
//		}

		System.out.print("저자입력>> ");
		String author = scn.nextLine();
		System.out.print("춢판사입력>> ");
		String company = scn.nextLine();
		System.out.print("금액입력>> ");
		int price = Integer.parseInt(scn.nextLine());
//		if(title.isBlank() || author.isBlank() || company.isBlank()) {
//			System.out.println("항목을 입력해주세요.");
//			break;
//		}				
		Book book = new Book(title, author, company, price, getSequnceNo());
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] == null) {
				bookStore[i] = book;
				System.out.println((i + 1) + "번째 " + title + "/" + author + "/" + company + "/" + price + " 등록되었습니다");
				break;
			}
		}
	} // end of add().
		// 수정

	public  void edit() {
		System.out.print("수정할 책이름 입력>> ");
		String title = scn.nextLine();
		boolean isExist = false;

		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
				boolean choiceRun = true;
				while (choiceRun) {
					System.out.println("1.제목 2.저자 3.출판사 4.금액 9.수정취소");
					System.out.print(bookStore[i].getTitle() + "의 수정할 내용을 선택해주세요.");
					int choice = Integer.parseInt(scn.nextLine());
					switch (choice) {
					case 1:
						System.out.print(bookStore[i].getTitle() + "의 수정할 책제목을 입력해주세요.");
						title = scn.nextLine();
						bookStore[i].setTitle(title);
						System.out.println("수정되었습니다.");
						isExist = true;
						choiceRun = false;
						break;
					case 2:
						System.out.print(bookStore[i].getTitle() + "의 수정할 저자를 입력해주세요.");
						String author = scn.nextLine();
						bookStore[i].setAuthor(author);
						System.out.println("수정되었습니다.");
						isExist = true;
						choiceRun = false;
						break;
					case 3:
						System.out.print(bookStore[i].getTitle() + "의 수정할 출판사를 입력해주세요.");
						String company = scn.nextLine();
						bookStore[i].setCompany(company);
						System.out.println("수정되었습니다.");
						isExist = true;
						choiceRun = false;
						break;
					case 4:
						System.out.print(bookStore[i].getTitle() + "의 수정할 금액을 입력해주세요.");
						int price = Integer.parseInt(scn.nextLine());
						bookStore[i].setPrice(price);
						System.out.println("수정되었습니다.");
						isExist = true;
						choiceRun = false;
						break;
					case 9:
						choiceRun = false;
						isExist = true;
						break;
					default:
						System.out.println("다시 선택해주세요.");
					}
				}
			}
		}
		if (!isExist) {
			System.out.println("없는 이름입니다.");
		}
	} // end of edit().

	public  void delete() {
		String title = "";
		// 반드시 값을 입력받도록.
		System.out.print("삭제할 제목 입력>> ");
		while (true) {
			title = scn.nextLine();
			if (!title.isBlank()) {
				break;
			}
			System.out.print("제목을 입력하세요>> ");
		}

		boolean isExistD = false;
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
				bookStore[i] = null;
				System.out.println("삭제되었습니다.");
				isExistD = true;
				break;
			}
		}
		if (!isExistD) {
			System.out.println("없는 이름입니다.");
		}
	}

	public  void list() {
		// 순번정렬
		// 순번1 > 순번2, 제외:순번2(null), 순번1(null)
		Book temp = null;
		for (int j = 0; j < bookStore.length - 1; j++) {
			for (int i = 0; i < bookStore.length - 1; i++) {
				if (bookStore[i + 1] == null) { // 변경 필요없음
					continue;
				}
				if (bookStore[i] == null // 변경 필요
						|| bookStore[i].getOrderNo() > bookStore[i + 1].getOrderNo()) {
					temp = bookStore[i];
					bookStore[i] = bookStore[i + 1];
					bookStore[i] = temp;
				}
			}
		} // end of for.

		int seqNo = 1;
		System.out.println("순번  제목    저자    가격");
		System.out.println("============================");
		Book[] list = searchList(null);
		for (Book bok : list) {
			if (bok != null) {
				System.out.println(seqNo++ + " " + bok.showList());
			}
		}
		System.out.println("============================");
	} // end of list()

	public  void listCompany() {
		System.out.print("조회할 출판사 정보>>");
		String company = scn.nextLine();

		int seqNo = 1;
		System.out.println("순번  제목    저자    가격");
		System.out.println("============================");
		Book[] list = searchList(company);
		for (Book bok : list) {
			if (bok != null) {
				if (bok.getCompany().equals(company)) {
					System.out.println(seqNo++ + " " + bok.showList());
				}
			}
		}
		System.out.println("============================");

	} // end of listCompany

	// list와 listCompany에서 활용할 공통메소드
	public  Book[] searchList(String keyword) {
		Book[] list = new Book[100];
		int idx = 0;
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null) {
				if (keyword == null 
				|| bookStore[i].getCompany().equals(keyword)) {
					list[idx++] = bookStore[i];
				}
			}
		}
		return list;
	}

	public  void bookInfo() {
		String title = "";
		// 반드시 값을 입력받도록.
		System.out.print("조회할 책 제목 입력>> ");
		while (true) {
			title = scn.nextLine();
			if (!title.isBlank()) {
				break;
			}
			System.out.print("제목을 입력하세요>> ");
		}
		Book result = searchBook(title);
		if (result == null) {
			System.out.println("해당하는 책 제목이 없습니다.");
			return;
		}
		System.out.println(result.showBookInfo());
	}

	// 도서명으로 조회하는 기능
	public  Book searchBook(String title) { // 책 제목 중복 확인 시 사용
		for (int i = 0; i < bookStore.length; i++) {
			if (bookStore[i] != null && bookStore[i].getTitle().equals(title)) {
				return bookStore[i]; // 조회결과가 있을 경우에는 Book 반환
			}
		}
		return null; // 조회결과가 없을 시 null 반환 >> 책 제목에 중복이 없으므로 입력 진행
	}
//	public static void companyBookList() {
//		String company = "";
//		// 반드시 값을 입력받도록.
//		System.out.print("조회할 출판사 입력>> ");
//		while (true) {
//			company = scn.nextLine();
//			if (!company.isBlank()) {
//				break;
//			}
//			System.out.print("출판사를 입력하세요>> ");
//		}
//		for(int i = 0; i < bookStore.length; i++) {
//			if(bookStore[i] != null && bookStore[i].getCompany().equals(company)) {
//				System.out.println(bookStore[i].showList());
//			}
//		}
//	}

	public void main(String[] args) {
		// 저장공간,
		init();
		bookUser();
		boolean run = true;

		while (run) {
			System.out.println("1.도서등록 2.수정 3.삭제 4.목록 5.상세조회 6.목록조회(출판사) 9.종료");
			System.out.print("선택>> ");
			int menu = Integer.parseInt(scn.nextLine());
			switch (menu) {
			case 1: // 등록.
				add();
				break;
			case 2: // 수정.
				edit();
				break;
			case 3: // 삭제.
				delete();
				break;
			case 4: // 목록.
				list();
				break;
			case 5: // 상세조회
				bookInfo();
				break;
			case 6: // 출판사 목록조회
//				companyBookList();
				listCompany();
				break;
			case 9: // 종료.
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요.");
			}
		}
		System.out.println("end of prog.");
	} // end of main().

}
