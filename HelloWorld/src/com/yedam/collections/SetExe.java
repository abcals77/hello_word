package com.yedam.collections;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.yedam.variable.Member;

public class SetExe {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>(); // 중복값 X
		set.add("Hello");
		set.add("World");
//		set.add(100);
		set.add("Hello");
		
		Iterator<String> iter = set.iterator(); // 반복자
		while(iter.hasNext()){
			String result = iter.next();
			System.out.println(result);
		}
		
		// Set<Member> 중복값 허용 X
		Set<Member> members = new HashSet<>();
		members.add(new Member("홍길동", 80));
		members.add(new Member("김민규", 85));
		members.add(new Member("홍길동", 80));
		
		// 입력값은 같아도 주소값이 다름 => 다른 객체취급
		Iterator<Member> miter = members.iterator();
		while(miter.hasNext()) {
			Member result = miter.next();
			System.out.println(result.toString()); 
		}
		System.out.println("====================");
		// int => Integer
		int[] intAry = {10, 20, 30, 40, 20, 10};
		// int배열에서 중복된 값을 제거한 결과 List 추가.
		// List 반복문 활용 출력
		Set<Integer> setIntAry = new HashSet<Integer>();
		for(int i = 0; i < intAry.length; i++) {
			setIntAry.add(intAry[i]);
		}
		List<Integer> listIntAry = new ArrayList<Integer>();
		Iterator<Integer> iter2 = setIntAry.iterator();
		while(iter2.hasNext()) {
			listIntAry.add(iter2.next());
		}
//		for(int i = 0; i < listIntAry.size(); i++) {
//			System.out.println(listIntAry.get(i));
//		}
		listIntAry.forEach(num -> System.out.println(num));
		
		
		
		
		
		
		
		
	}
}
