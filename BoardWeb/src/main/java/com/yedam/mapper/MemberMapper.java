package com.yedam.mapper;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.MemberVO;

public interface MemberMapper {
	MemberVO selectMember(@Param("id") String id, @Param("pw") String pw);
		
	
}
