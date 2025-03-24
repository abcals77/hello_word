package com.yedam.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MemberJdbc {
	// Connection 생성.
	Connection getConnect() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userId = "scott";
		String userPw = "tiger";
		try {
			Connection conn = DriverManager.getConnection(url, userId, userPw);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Member login(String id, String pw) {
		Connection conn = getConnect();
		String sql = "select * from tb_member where user_id = ? and user_pw = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				Member member = new Member(rs.getInt("user_serial"),
									rs.getString("user_id"),
									rs.getString("user_pw"),
									rs.getString("user_name"),
									rs.getString("user_email"),
									rs.getString("user_date"),
									rs.getInt("user_status"));
				return member;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; // 조회결과가 없으면...
	} // member end login
	
	public Member sign(String id, String pw, String name, String email) {
		

		return null;
	}
	
	
	
	
}
