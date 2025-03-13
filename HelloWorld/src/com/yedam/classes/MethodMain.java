package com.yedam.classes;

public class MethodMain {
	public static void main(String[] args) {
		MethodExe3 m3 = new MethodExe3();
//		System.out.println(m3.gugudan(2,5));
		m3.printStar(4, "*");
		m3.printStar2(4, "*");
		m3.printCard();
	}
	void method1() {
		MethodExe1 m1 = new MethodExe1();
//		System.out.println(m1);
		int n = 5;
		m1.printString(n,"★");
		
		double result = m1.sum(n, 10.5);
		System.out.println(result);
	
		int sum = m1.sum(new int[] {10, 20, 30});
		
		System.out.println(sum);
		
		double doubleSum = m1.sum(new double[] {10.5, 12.1, 16.8});
		
		System.out.println(doubleSum);
	}
	
	void method2() {
		// 상품코드: M001, 상품명: 만년필, 가격: 10000
		MethodExe2 m2 = new MethodExe2();
		// 상품코드, 상품명, 가격 modify(Product prod)
		Product prod = new Product("A002", "지우개",1000);
		if(m2.modify(prod)) {
			System.out.println("수정성공");
		}else {
			System.out.println("수정실패");
		}
		
 		if(m2.remove("B001")) {
			System.out.println("삭제성공");
		}else {
			System.out.println("삭제실패");
		}
		
		if(m2.add(new Product("M001","만년필",10000))) { // 잘 등록되면 true 등록되지 못하면 false
			System.out.println("등록완료");			
		};
		
		Product search = new Product();
		search.setProductName("지우개"); // 상품코드, 상품명, 가격
		search.setPrice(700);
		
		Product[] list = m2.productList(search); // 
		for (int i = 0; i < list.length; i++) { // list 배열 for문으로 반환
			if (list[i] != null) {
				System.out.println(list[i].showList());				
			}
		}
	}
}
