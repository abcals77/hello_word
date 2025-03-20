package com.yedam.bookApp;

import java.util.List;
import java.util.Scanner;

/*
 * 등록, 수정, 삭제, 목록
 */

public class BookMain {

	// 2. 정적필드. 할당.
	private static BookMain instance = new BookMain();

	// 1. 생성자 private 선언.
	private BookMain() {
	}

	// 3. getInstance() 제공.
	public static BookMain getInstance() {
		return instance;
	}

	// 저장공간.
//	Book[] bookStore = new Book[100];
//	User[] members = { new User("aaa", "홍길동", "aaa")//
//			, new User("user02", "김민규", "2222")//
//			, new User("user03", "김민식", "3333") };

	// 스캐너 객체선언.
	Scanner scn = new Scanner(System.in);
	// jdbc 처리.
	BookJdbc dao = new BookJdbc();

	// 아이디, 비밀번호 입력.
	private User login(String id, String pw) {
		MemberJdbc dao = new MemberJdbc();
		return dao.login(id, pw);
		//		for (int i = 0; i < members.length; i++) {
//			if (members[i].getUserId().equals(id) //
//					&& members[i].getPassword().equals(pw)) {
//				return true;
//			}
//		}
//		return false;
	}

	// 순번생성.
	private int getSequnceNo() {
		int max = 0;
//		for (int i = 0; i < bookStore.length; i++) {
//			if (bookStore[i] != null //
//					&& bookStore[i].getOrderNo() > max) {
//				max = bookStore[i].getOrderNo();
//			}
//		}
		return max + 1; // 현재 마지막번호 + 1;
	} // end of getSequnceNo().

	// 등록.
	// 1. 이미 존재하는 제목은 입력불가.
	private void add() {
		
		System.out.print("도서코드입력>> ");
		String bookCode = scn.nextLine();
		
		System.out.print("제목입력>> ");
		String title = scn.nextLine();
		System.out.print("저자입력>> ");
		String author = scn.nextLine();
		System.out.print("출판사입력>> ");
		String company = scn.nextLine();
		System.out.print("금액입력>> ");
		String price = scn.nextLine();
		// 입력항목을 확인.
		if (bookCode.isBlank() || author.isBlank() || company.isBlank() || price.isBlank()) {
			System.out.println("항목을 입력하세요.");
			return; // 메소드 종료.
		}
		// Book 데이터를 생성.
		Book book = new Book(bookCode, title, author, company, Integer.parseInt(price));
		// 배열에 추가. -> ojdbc 변경.
		if (dao.insert(book)) {
			System.out.println("정상등록.");
		} else {
			System.out.println("등록예외.");
		}

	} // end of add().

	// 수정.
	private void edit() {
		// 책제목을 입력하지 않으면 메소드 종료하는 방식.
		System.out.print("도서코드입력>> ");
		String bcode = scn.nextLine();
		if (bcode.isBlank()) {
			System.out.println("도서코드을 반드시 입력.");
			return;
		}

		System.out.print("저자입력>> ");
		String author = scn.nextLine();
		System.out.print("제목입력>> ");
		String title = scn.nextLine();
		System.out.print("금액입력>> ");
		String price = scn.nextLine();
		System.out.print("출판사입력>> ");
		String company = scn.nextLine();

		// update(파라미터)
		Book book = new Book();
		book.setBookCode(bcode);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(Integer.parseInt(price));
		book.setCompany(company);
		
		// 조회 및 수정.
		// 찾는 책이 없을 경우에 메세지.
		if (dao.update(book)) {
			System.out.println("수정성공.");
		} else {
			System.out.println("수정예외.");
		}

	} // end of edit().

	private void delete() {
		// 책제목을 입력하지 않으면 반드시 값을 입력받는 방식.
		String bcode = "";
		while (true) {
			System.out.print("도서코드입력>> ");
			bcode = scn.nextLine();
			if (!bcode.isBlank()) { // 제목을 입력한 경우에..
				break;
			}
			System.out.println("도서코드을 입력하세요!!!");
		}
		// 삭제.
		// 찾는 책이 없을 경우에 메세지.
		if (dao.delete(bcode)) {
			System.out.println("삭제성공");
		} else {
			System.out.println("삭제예외");
		}
	} // end of delete().

	private void list() {
		// 순번정렬.
		// 순번1 > 순번2, 제외:순번2(null), 순번1(null)
		Book temp = null;

		int seqNo = 1;
		System.out.println("순번 코드 제목      저자   가격");
		System.out.println("==========================");
		List<Book> list = searchList("");
		for (Book bok : list) {
			if (bok != null)
				System.out.println(" " + seqNo++ + " " + bok.showList());
		}
	} // end of list().

	// list와 listCompany에서 활용할 공통메소드.
	private List<Book> searchList(String keyword) {
		List<Book> list = dao.list(keyword);
		return list;
	} // end of searchList.

	private void listCompany() {
		System.out.print("조회할 출판사 정보>> ");
		String company = scn.nextLine();

		int seqNo = 1;
		System.out.println("순번 제목      저자   가격");
		System.out.println("====================");
		List<Book> list = searchList(company);
		for (Book bok : list) {
			if (bok != null)
				System.out.println(seqNo++ + " " + bok.showList());
		}
	} // end of listCompany().

	private void bookInfo() {
		// 반드시 값을 입력받도록.
		String title = "";
		while (true) {
			System.out.print("도서코드입력>> ");
			title = scn.nextLine();
			if (!title.isBlank()) { // 제목을 입력한 경우에..
				break;
			}
			System.out.println("도서코드을 입력하세요!!!");
		}
		// 상세조회.
		Book result = searchBook(title);
		if (result == null) {
			System.out.println("조회결과가 없습니다.");
			return;
		}
		System.out.println(result.showBookInfo());

	} // end of bookInfo().

	// 도서명으로 조회하는 기능.
	private Book searchBook(String bcode) {
		return dao.select(bcode); // 조회결과가 없을 경우에는 null을 반환.
	} // end of searchBook(String title).

	public void main(String[] args) {
		// id, password 확인.
		while (true) {
			System.out.println("id입력>> ");
			String id = scn.nextLine();
			System.out.println("password입력>> ");
			String pw = scn.nextLine();
			// User 클래스, Map 컬렉션.
			User user = login(id, pw);
			if (user != null) {
				System.out.println(user.getUserName() + ", 환영합니다.");
				break;
			}
			// id, password 비정상.
			System.out.println("id와 password를 확인하세요.");
		}

		init();
		boolean run = true;
		while (run) {
			System.out.println("1.도서등록 2.수정 3.삭제 4.목록 5.상세조회 6.목록조회(출판사) 9.종료");
			System.out.print("선택>> ");
			// 예외처리.
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
			case 1: // 등록.
				add();
				break;
			case 2: // 수정. 도서명으로 검색, 금액을 수정.
				edit();
				break;
			case 3: // 삭제. 도서명으로 검색 후 삭제.
				delete();
				break;
			case 4: // 목록.
				list();
				break;
			case 5: // 상세조회.
				bookInfo();
				break;
			case 6: // 목록(출판사)
				listCompany();
				break;
			case 9: // 종료.
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요");
			}
		}
		System.out.println("end of prog.");
	} // end of main().

	private void init() {
//		bookStore[0] = new Book("이것이자바다", "신용권", "한빛출", 20000, 1);
//		bookStore[1] = new Book("스크립트기초", "박기초", "우리출", 26000, 2);
//		bookStore[2] = new Book("HTML,CSS", "김하늘", "가람출", 25000, 3);
	}
}
