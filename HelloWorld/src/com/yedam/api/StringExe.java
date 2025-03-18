package com.yedam.api;

public class StringExe {
	public static void main(String[] args) {
		String str = "Hello, world";
		str = new String("Hello, world");
		byte[] bAry = str.getBytes();
		for(int i = 0; i < bAry.length; i++) {
			System.out.println(bAry[i]);
		}
		
		byte[] bstr = {72,101,108,108,111,44,32,119,111,114,108,100};
		String msg = new String(bstr, 7, 5);
		System.out.println(msg);
		char result = msg.charAt(0); // 실제 값은 정수, 보여지는 것은 문자
		System.out.println(result);
		System.out.println((int)result);
		
		// String : "", char : ''
		if(result == 'W') {
			// 비교구문.
		}
		// 특정 문자가 있으면 해당 문자의 위치 반환, 없을 시 -1 반환
		int idx = msg.indexOf("o");
		if(idx != -1) {
			// 처리.
		}
		
		String[] names = {"홍길동","홍길승","김민규","박홍길"};
		int cnt = 0;
		for(int i = 0; i < names.length; i++) {
			if(names[i].indexOf("홍") == 0) {
				cnt++;
			}
		}
		System.out.println("\"홍\"을 성으로 하는 이름의 갯수: " + cnt);
		
		System.out.println("Hello, World".substring(3, 7));
		
		String ssn = "010624-1230123";
		char sex = ssn.charAt(7);
		switch (sex){
			case '1':
			case '3':
				System.out.println("남자 입니다.");
				break;
			case '2':
			case '4':
				System.out.println("여자 입니다.");
				break;	
		}
		
		String strVar1 = new String("신민철");
		String strVar2 = "신민철";
		
		if(strVar1 == strVar2) {
			System.out.println("같은 String 객체를 참조");
		} else {
			System.out.println("다른 String 객체를 참조");
		}
		
		if(strVar1.equals(strVar2)) {
			System.out.println("같은 문자열을 가짐");
		} else {
			System.out.println("다른 문자열을 가짐");
		}
		
		
		
		
		
	}
}
