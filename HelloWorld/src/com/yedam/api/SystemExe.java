package com.yedam.api;

public class SystemExe {
	public static void main(String[] args) {
		
		for(int i = 1; i <= 10; i++) {
			if(i == 5) {
//				break;
				System.out.println("종료합니다.");
//				System.exit(0);
			}
		}
		System.out.println("");
		
		long time1 = System.nanoTime();
		
		int sum = 0;
		for(int i = 1; i <= 1000000; i++) {
			sum += i;
		}
		
		long time2 = System.nanoTime();
		
		System.out.println("1~1000000까지의 합: " + sum);
		System.out.println("계산에 " + (time2 - time1) + " 나노초가 소요되었습니다.");
	}
}
