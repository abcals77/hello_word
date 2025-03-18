package com.yedam.api;

public class StringUtil {
	// 1.성별
	static String getGender(String ssn) {
//		int pos = -1;
//		if(ssn.length() == 13) {
//			pos = 6;
//		} else if(ssn.length() == 14) {
//			pos = 7;
//		}
//		char gNo = ssn.charAt(pos);
		
		String newStr = ssn.replace(" ", "");
		String newStr2 = newStr.replace("-", "");
		char sex = newStr2.charAt(6);
		String gender = "";
		switch (sex) {
		case '1':
		case '3':
			gender = "남";
			break;
		case '2':
		case '4':
			gender = "여";
			break;
		default:
			return "알수없음";
		} 
		return ssn + "님은 "+ gender + "자 입니다."; // "남" 또는 "여" 반환.
	}
	// 2.파일명
	static String getFileName(String file) {
//		int pos = 0;
//		String fileName = "";
//		while (true) {
//			int idx = file.indexOf("/",pos); // 찾을 값, 찾을 위치의 시작값
//			if(idx == -1) {
//				fileName = file.substring(pos, file.indexOf("."));
//				break;
//			}
//			pos = idx + 1;
//		}
//		return fileName;
		
		int index = file.indexOf(".");
		int index2 = file.lastIndexOf("/");	
		String str = file.substring(index2 + 1,index);
		return str;
	}
	// 3.파일확장자
	static String getExtName(String file) {
		int index = file.indexOf(".");
		String str = file.substring(index + 1);
		return str;
	}
}
