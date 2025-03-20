package com.yedam.classes;

import java.util.List;
import java.util.Scanner;

public class MethodMain {

	public static void main(String[] args) {
		
		
		
		officeApp();
		System.out.println("end of prog");
	} // end of main

	static void officeApp() {
		Scanner scn = new Scanner(System.in);
		// 목록, 추가, 수정, 삭제......
		MethodExe2 m2 = new MethodExe2(); // 기능정의.

		// 사용자입력 받아서 1.목록, 2.추가, 3.수정, 4.삭제, 9.종료 구현
		// 입력 메세지 정의 구현.
		boolean run = true;
		while (run) {
			System.out.println("1.목록  2.추가  3.수정  4.삭제  5.조건조회  9.종료");
			System.out.print("선택>> ");
			int menu = Integer.parseInt(scn.nextLine());
			switch (menu) {
			case 1: // 목록
				System.out.println("상품코드     상품명      가격");
				System.out.println("============================");
				Product prd = new Product();
				prd.setProductName("ALL");
				List<Product> listing = m2.productList(prd);
				for (int i = 0; i < listing.size(); i++) {
					System.out.println(listing.get(i).showList());
				}
				break;
			case 2: // 추가
				Product prdAdd = new Product();
				prdAdd.setProductName("ALL");
				List<Product> listAdd = m2.productList(prdAdd);
				System.out.print("상품코드를 입력해주세요>> ");
				String code = scn.nextLine();
				
				if (code.isBlank()) {
					System.out.println("상품코드를 입력해주세요.");
					break;
				} else {
					boolean addRun = false;
					for (int i = 0; i < listAdd.size(); i++) {
						if (listAdd.get(i).getProductCode().equals(code)) {
							System.out.println("중복된 상품코드 제목입니다.");
							addRun = true;
							break; // for문 종료 break
						}
					}
					if(addRun) { // case 종료 break;
						break;
					}
				}
				
				System.out.print("품명을 입력해주세요>> ");
				String name = scn.nextLine();
				System.out.print("가격을 입력해주세요>> ");
				int price = Integer.parseInt(scn.nextLine());
				
				if (m2.add(new Product(code, name, price))) { // 잘 등록되면 true 등록되지 못하면 false
					System.out.println("등록완료");
				}
				
				break;
			case 3: // 수정
				System.out.print("수정할 상품코드를 입력해주세요>> ");
				String modifyCode = scn.nextLine();
				System.out.print("수정 품명을 입력해주세요>> ");
				String modifyName = scn.nextLine();
				System.out.print("수정 가격을 입력해주세요>> ");
				int modifyPrice = Integer.parseInt(scn.nextLine());
				
				
				Product prod = new Product(modifyCode, modifyName, modifyPrice);
				if (m2.modify(prod)) {
					System.out.println("수정성공");
				} else {
					System.out.println("수정실패");
				}
				break;

			case 4: // 삭제
				System.out.print("삭제할 상품코드를 입력해주세요>> ");
				String deleteCode = scn.nextLine();
				if (m2.remove(deleteCode)) {
					System.out.println("삭제성공");
				} else {
					System.out.println("삭제실패");
				}
				break;
			case 5: // 조건조회
				System.out.print("품명을 입력해주세요>> ");
				String searchName = scn.nextLine();
				System.out.print("가격을 입력해주세요>> ");
				int searchPrice = Integer.parseInt(scn.nextLine());

				Product search = new Product();
				search.setProductName(searchName); // 상품코드, 상품명, 가격
				search.setPrice(searchPrice);

				List<Product> list = m2.productList(search); //
				for (int i = 0; i < list.size(); i++) { // list 배열 for문으로 반환
						System.out.println(list.get(i).showList());
				}
				break;

			case 9: // 종료
				System.out.println("프로그램을 종료합니다.");
				run = false;
				m2.save();
				break;
			default:
				System.out.println("메뉴를 다시 선택하세요.");
			}

		}
	}

	void method1() {
		MethodExe1 m1 = new MethodExe1();
//		System.out.println(m1);
		int n = 5;
		m1.printString(n, "★");

		double result = m1.sum(n, 10.5);
		System.out.println(result);

		int sum = m1.sum(new int[] { 10, 20, 30 });

		System.out.println(sum);

		double doubleSum = m1.sum(new double[] { 10.5, 12.1, 16.8 });

		System.out.println(doubleSum);
	}

	void method2() {
		// 상품코드: M001, 상품명: 만년필, 가격: 10000
		MethodExe2 m2 = new MethodExe2();
		// 상품코드, 상품명, 가격 modify(Product prod)
		Product prod = new Product("A002", "지우개", 1000);
		if (m2.modify(prod)) {
			System.out.println("수정성공");
		} else {
			System.out.println("수정실패");
		}

		if (m2.remove("B001")) {
			System.out.println("삭제성공");
		} else {
			System.out.println("삭제실패");
		}

		if (m2.add(new Product("M001", "만년필", 10000))) { // 잘 등록되면 true 등록되지 못하면 false
			System.out.println("등록완료");
		}
		

		Product search = new Product();
		search.setProductName("지우개"); // 상품코드, 상품명, 가격
		search.setPrice(700);

		List<Product> list = m2.productList(search); //
		for (int i = 0; i < list.size(); i++) { // list 배열 for문으로 반환
				System.out.println(list.get(i).showList());
		}
	}

	void method3() {
//		MethodExe3 m3 = new MethodExe3();
//		System.out.println(m3.gugudan(2,5));
		MethodExe3.printStar(4, "*");
		MethodExe3.printStar2(4, "*");
		MethodExe3.printCard();
	}

	void method4() {
//		MethodExe4 m4 = new MethodExe4();
		MethodExe4.main();

	}
}
