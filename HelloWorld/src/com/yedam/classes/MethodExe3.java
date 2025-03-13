package com.yedam.classes;

public class MethodExe3 {

	// 구구단출력.
	String gugudan(int num, int toNum) {
		// 2 * 1 = 2
		// 2 * 2 = 4
		// ...
		String str = "";
		for (int i = num; i <= toNum; i++) {
			str += "===== " + i + "단 ===== \n";
			for (int j = 1; j < 10; j++) {
//				System.out.println(i + " * " +  j + " = " + (i * j));
				str += i + " * " + j + " = " + (i * j) + "\n";
			}
		}
		return str;
	} // end of gugudan.

	void printStar(int cnt, String str) {
		String line = "";
		for (int i = 1; i <= cnt; i++) {
			line += "\n";
			for (int j = 1; j <= i; j++) {
				line += str;

			}
		}
		System.out.println(line);
	}

	void printStar2(int cnt, String str) {
		String line = "";
		for (int i = 1; i <= cnt; i++) {
			line += "\n";
			for (int n = 1; n <= (cnt - i); n++) {
				line += " ";
			}
			for (int j = 1; j <= i; j++) {
				line += str;
			}
		}
		System.out.println(line);
	}

	void printCard() {
		// 배열선언.
		int[] intAry = new int[16];
		// 1 ~ 16까지의 임의수 할당.
		
		for (int i = 0; i < intAry.length; i++) {
			int temp = (int) (Math.random() * 16) + 1;;	
			for (int j = 0; j < intAry.length; j++) {
				 
				
				
			}
		}
		// 출력.
		for (int i = 0; i < intAry.length; i++) {
			System.out.printf("%3d", intAry[i]);
			if (i % 4 == 3) {
				System.out.println();
			}

		}
	}
}
