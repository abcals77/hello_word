package com.yedam.ref;

public class ArrayExe3Calendar {
	// 2025년 기준으로 월 정보 => 1일의 위치를 반환.
	public static int getFirstDay(int month) {
		switch(month) {
		case 1:
			return 3;
		case 2:
			return 6;
		case 3:
			return 6;
		case 4:
			return 2;
		default:
			return 1;
		}
	}
	// 2025년 기준으로 월의 마지막날 반환.
	public static int getLastDate(int month) {
		int date = 31;
		switch(month) {
		case 2:
			date = 28;
			break;
		case 4:
			date = 30;
			break;
		case 6:
			date = 30;
			break;
		case 9:
			date = 30;
			break;
		case 11:
			date = 30;
			break;
		}
		return date;
	}
	
	public static void main(String[] args) {
		// 1 ~ 31 콘솔출력
		String days[] = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		for(String day : days) {
			System.out.print(" " + day);
		}
		// 날짜출력. "1" -> 1 : Integer.parseInt();
		System.out.println();
		int month = 2;
		int space = getFirstDay(month); // 1일의 위치값.
		int lastDate = getLastDate(month); // 월의 마지막날.
		// 공백갯수만큼 빈칸.
		for (int i = 0; i < space; i++) {
			System.out.print("    ");
		}
		for(int i = 1; i <= lastDate; i++) {
			if(String.valueOf(i).length() == 1) { // String.valueOf 정수를 문자로 변환한 후 length 글자 수 체크
				System.out.print("   " + i);
			}else {
				System.out.print("  " + i);
			}
			// 줄바꿈
			if((space + i) % 7 == 0) {
				System.out.println();
			}
			
		}
	} // end of main()
}
