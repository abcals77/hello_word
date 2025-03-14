package com.yedam.classes;
/*
 * 정적(static) 멤버의 사용
 */
public class MethodExe4 {
	
	static void test() {
		System.out.println("인스턴스 메소드");
	} //
	
	static void main() {
		test();
	} // static : 클래스 생성과 동시에 사용 가능상태
}
