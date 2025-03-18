package com.yedam.api;

import java.util.ArrayList;
import java.util.List;

public class WrapperExe {
	public static void main(String[] args) {
		int[] intAry = {10, 20};
		List<Integer> list = new ArrayList<Integer>();
		
		list.add(null); // int -> Integer 박싱. Integer -> int 언박싱
		list.add(new Integer(20));
		
		//
		int num1 = 10; // ==(비교 연산자) 두개의 값을 비교
		Integer num2 = new Integer(10); // == 주소값을 비교 => false.
		System.out.println(num1 == num2.intValue());
		
	}
}
