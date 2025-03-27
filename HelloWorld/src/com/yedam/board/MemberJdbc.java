package com.yedam.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MemberJdbc {
	// Connection 생성.
	Connection getConnect() {
		String url = "jdbc:oracle:thin:@192.168.0.31:1521:xe";
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
		String sql = "select * from tb_member where user_id = ? and user_pw = ? and user_status = 1";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			ResultSet rs = psmt.executeQuery();
			Member member = null;
			if(rs.next()) {
				member = new Member(rs.getInt("user_serial"),
									rs.getString("user_id"),
									rs.getString("user_pw"),
									rs.getString("user_name"),
									rs.getString("user_email"),
									rs.getString("user_date"),
									rs.getInt("user_status"));
			}

			conn.close();	
			return member;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; // 조회결과가 없으면...
	} // member end login
	public List<Member> memberList(){
		List<Member> list = new ArrayList<Member>();
		Connection conn = getConnect();
		String sql = "select * from tb_Member";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Member member = new Member();
				member.setUserSerial(rs.getInt("user_serial"));
				member.setUserId(rs.getString("user_id"));
				member.setUserPw(rs.getString("user_pw"));
				member.setUserName(rs.getString("user_name"));
				member.setUserEmail(rs.getString("user_email"));
				member.setUserDate(rs.getString("user_date"));
				list.add(member);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	
	public boolean memberSignUp(Member member) {
		Connection conn = getConnect();
		String sql = "insert into tb_member(\r\n"
				+ "  user_serial,\r\n"
				+ "  user_id,\r\n"
				+ "  user_pw,\r\n"
				+ "  user_name,\r\n"
				+ "  user_email,\r\n"
				+ "  user_date,\r\n"
				+ "  user_status)\r\n"
				+ "values(\r\n"
				+ "  tb_member_sequence.nextval,\r\n"
				+ "  ?,\r\n"
				+ "  ?,\r\n"
				+ "  ?,\r\n"
				+ "  ?,\r\n"
				+ "  sysdate,\r\n"
				+ "  1)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getUserId());
			stmt.setString(2, member.getUserPw());
			stmt.setString(3, member.getUserName());
			stmt.setString(4, member.getUserEmail());
			
			int r = stmt.executeUpdate();
			if (r > 0) {
				conn.close();
				return true; // 등록성공
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean memberDelete(String userId, String userPw) {
		Connection conn = getConnect();
		String sql = "update tb_member set user_status = -1 where user_id = ? and user_pw = ?";

		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			psmt.setString(2, userPw);
			int r = psmt.executeUpdate();
			if (r > 0) {
				conn.close();
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

}
