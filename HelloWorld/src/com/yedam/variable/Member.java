package com.yedam.variable;
// public <-> private : public 다른 클래스에서 접근 가능, private 다른 클래스에서 접근 불가
public class Member {
	// 클래스 : 필드(값을 저장)
	private String name;
	private int score; // 
	// 클래스 : 생성자(실체 생성)
	// 컴파일러 기본생성자 생성.
	public Member() { // 기본 생성자
		
	}
	// 생성자 재정의(overloading) => 동일 생성자를 여러 방식으로 이용
	public Member(String name, int score) { // 사용자가 생성자를 정의하면 기본 생성자는 사라짐
		this.name = name;
		this.score = score;
	}
	
	// Set컬렉션의 중복된 값으로 지정.
	// name, score => hashCode + equals => 동일한 값
	@Override
	public int hashCode() {

		return score;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member target = (Member) obj;
			return this.name.equals(target.name);
		}
		return false;
	}
	
		
	// 클래스 : 메소드(기능) = 반환값 메소드명 (매개값)
	public void showInfo() {
		System.out.println("이름은 " + name + ", 점수는 " + score);
	}
	public void setMember(String name, int score) {
		this.name = name; // this. 필드(class) name
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	public void setName(String name) {
		this.name = name;
		
	}
	
	// toString 재정의
	public String toString() {
		return "이름: " + name + ", 점수: " + score;
	}
	
	
	
	
	// score 필드의 값을 지정
	public void setScore(int score) {
		if(score < 0 || score > 100) {
			System.out.println("적정값을 입력하세요.");
			return; // 메소드의 종료.
		}
		this.score = score;
	}
}
