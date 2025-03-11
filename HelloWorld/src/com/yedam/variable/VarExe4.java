package com.yedam.variable;

import java.util.Scanner;

public class VarExe4 {
	public static void main(String[] args) {
		int[] scores = new int[3]; // {0, 0, 0} 정수 배열을 3개 담을 수 있는 공간
		// 점수를 입력하세요.
		// 값 입력.
		// 입력값의 합을 콘솔에 출력
		Scanner scn = new Scanner(System.in);


		int sum = 0;
		double avg = 0.0;
		for(int i = 0; i < scores.length; i++) {
			System.out.print("점수를 입력하세요>");
			scores[i] = scn.nextInt();
			sum += scores[i];
		}
		System.out.println("합은 " + sum + "입니다.");
		avg = sum * 1.0 / scores.length;
		System.out.println("평균은 " + avg + "입니다.");
		scn.close();
	}
}
