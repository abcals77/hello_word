package com.yedam.api;

import java.util.Calendar;

public class CalendarExe {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2024); // cal객체 년 정보를 2024로 변경
		cal.set(Calendar.MONTH, 1); // 2월.
		cal.set(Calendar.DATE, 10); // 10일.
//		cal.set(2023, 2, 5); // 2023년 3월 5일.
//		
//		System.out.println(cal.get(Calendar.YEAR));
//		System.out.println(cal.get(Calendar.MONTH));
//		System.out.println(cal.get(Calendar.DATE));
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK)); // 요일
//		System.out.println(cal.getActualMaximum(Calendar.DATE)); // 월의 마지막날
		
		System.out.println(getDay(2025, 7)); // 7월달의 1일의 요일
		System.out.println(getLastDate(2025, 7)); // 7월달의 마지막날
		
	}
	
	// 년, 월 입력 => 1일의 요일 반환, 말일을 반환
	static String getDay(int year, int month) {
//		Calendar today = Calendar.getInstance();
//		today.set(year, month - 1, 1);
//		int day = today.get(Calendar.DAY_OF_WEEK);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		
		int day = cal.getActualMinimum(Calendar.DATE);
		cal.set(Calendar.DATE, day);
		int temp = cal.get(Calendar.DAY_OF_WEEK);
		String week = "";
		switch(temp) {
		case 1: week = "일요일";
			break;
		case 2: week = "월요일";
			break;
		case 3: week = "화요일";
			break;
		case 4: week = "수요일";
			break;
		case 5: week = "목요일";
			break;
		case 6: week = "금요일";
			break;	
		case 7: week = "토요일";
			break;
		}		
		return year + "년 " + month + "달 1일의 요일은 " + week;
	}
	static String getLastDate(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		int day = cal.getActualMaximum(Calendar.DATE);
		
		return year + "년 " + month + "달 마지막일은 " + day + "일";
	}
}
