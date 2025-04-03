package com.yedam.service;

import com.yedam.vo.MemberVO;

// 업무프로세스(Service)
public interface MemberService {

	// 로그인
	MemberVO login(String id, String pw);
	boolean addMember(MemberVO member); // 회원등록
	
}
