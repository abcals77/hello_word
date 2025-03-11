package com.yedam.variable;

import java.util.Scanner;

// 예금, 춝므, 잔고 확인기능.

public class VarExe5 {
	public static void main(String[] args) {
		boolean run = true;
		int balance = 0; // 예금액을 저장하는 변수
		// 예금 최대 10만원, 잔고 금액 이상 출금 방지, 잔액 > 0
		Scanner scn = new Scanner(System.in);
		while(run) {
			System.out.println("1.예금 2.출금 3.잔고 4.종료");
			int menu = scn.nextInt();
			int a = 0;
			switch(menu) {
			case 1: 
				System.out.print("금액을 입력>>");
				a = scn.nextInt();
				if(balance + a >= 100000) {
					System.out.println("예금 금액은 10만원을 넘을 수 없습니다.");
				}else {
					balance += a;	
				}
				System.out.println("잔고 : " + balance);
				break; // case 1 종료.
			case 2:
				System.out.print("출금 금액을 입력>>");
				a = scn.nextInt();
				if(balance < a) {
					System.out.println("출금 금액은 잔고 금액을 넘을 수 없습니다.");
				}else {
					balance -= a;
				}
				System.out.println("잔고 : " + balance);
				break; // case 2 종료.
			case 3:
				System.out.println("잔고 : " + balance);
				break; // case 3 종료.
			case 4:
				run = false;
			}
		}
		System.out.println("end of prog.");
		scn.close();
	} // end of main();
}
