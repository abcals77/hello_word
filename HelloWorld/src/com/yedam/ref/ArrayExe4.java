package com.yedam.ref;

public class ArrayExe4 {

	public static void main(String[] args) {
		int[] intAry = { 8, 7, 10, 5, 4, 21, 102, 52 };

		for (int j = 0; j < intAry.length - 1; j++) {
			for (int i = 0; i < intAry.length - 1; i++) {
				if (intAry[i] > intAry[i + 1]) {
					int temp = intAry[i];
					intAry[i] = intAry[i + 1];
					intAry[i + 1] = temp;
				}
			}
		}
//		for(int i = 0; i < intAry.length - 1; i++) {
//			if(intAry[i] > intAry[i + 1]) {
//				int temp = intAry[i];
//				intAry[i] = intAry[i+1];
//				intAry[i+1] = temp;
//				
//			}
//		}
		for (int i = 0; i < intAry.length; i++) {
			System.out.print(" " + intAry[i]);
		}
	}
}
