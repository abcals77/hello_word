package com.yedam;

import com.yedam.classes.MethodMain;

public class HelloExe {
	
	// 7. 이름, 연락처, 나이 선언.
	// 홍길동, 010-1234-1234, 20

	private String name;
	private String tel;
	private int age;
	HelloExe(String name, String tel, int age){
		this.name = name;
		this.tel = tel;
		this.age = age;
	}
	private static HelloExe[] people;
	
	// 8. [3]
	// 홍길동, 010-1234-1234, 20
	// 김민식, 010-2222-2222, 22
	// 최문식, 010-3333-3333, 23
	public static void peopleList(){
		people = new HelloExe[3];
		people[0] = new HelloExe("홍길동","010-1234-1234",20);
		people[1] = new HelloExe("김민식","010-2222-2222",22);
		people[2] = new HelloExe("최문식","010-3333-3333",23);
	}
	
	public static void main(String[] args) {
		peopleList();

		
		// 1. 32000 변수선언과 할당.
		int num = 32000;
		// 2. 34, 32, 88, 23
		int numAry[] = {34, 32, 88, 32};
		// 3. 문자 : 32
		String str = "32";
		// 4. 정수변수에 저장
		int num2 = Integer.parseInt(str);
		// 5. Hello, Nice, Good
		String strAry[] = {"Hello", "Nice", "Good"};
		// 6. 정수 5개 저장
		// Math.random() => 60 ~ 100
		int numAry2[] = new int[5];
		for(int i = 0; i < numAry2.length; i++) {
			numAry2[i] = (int) (Math.random() * 41 + 60);
			System.out.print(" " + numAry2[i]);
		}
		
		// 9. 나이가 제일 많은 사람의 이름을 출력.
		int max = 0;
		for(int i = 0; i < people.length; i++) {
			if(max < people[i].getAge()) {
				max = people[i].getAge();
			}
		}
		for(int i = 0; i < people.length; i++) {
			if(max == people[i].getAge()) {
				System.out.println(people[i].getName());
			}
		}
		
		
 	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

}

