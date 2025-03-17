package com.yedam.interfaces;
/*
 * Data Access Object
 * 인터페이스 내의 메소드는 추상메소드
 * MySql
 * Oracle
 */
public interface Dao {
	void insert();
	void update();
	void delete();
}