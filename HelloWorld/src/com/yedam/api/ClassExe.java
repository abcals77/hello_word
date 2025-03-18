package com.yedam.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yedam.bookApp.Book;

public class ClassExe {
	public static void main(String[] args) {
		
		try {
			Class cls = Class.forName("com.yedam.bookApp.Book"); // ClassNotFoundException.	
			// 클래스의 모든 메소드 반환
			Method[] methods = cls.getDeclaredMethods();
			for(int i = 0; i < methods.length; i++) {
				System.out.println(methods[i].getName()); // 메소드(메타) 정보 출력 
			}
			
			Field[] fary = cls.getDeclaredFields();
			for(int i = 0; i < fary.length; i++) {
				System.out.println(fary[i].getName()); // 
			}
			
			Constructor[] fcon = cls.getDeclaredConstructors();
			for(int i = 0; i < fcon.length; i++) {
				System.out.println(fcon[i].getName()); // 
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
