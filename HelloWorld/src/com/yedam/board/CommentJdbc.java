package com.yedam.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentJdbc {
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
		} // end of getConnect.
		
		public List<Comment> myCommentsList(int serial) {
			List<Comment> list = new ArrayList<Comment>();
			Connection conn = getConnect();
			String sql = "select * from tb_board_comment where user_serial = ? and comment_status = 1 order by comment_serial";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, serial);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					Comment comment = new Comment();
					comment.setCommentSerial(rs.getInt("comment_serial"));
					comment.setBoardSerial(rs.getInt("board_serial"));
					comment.setCommentContents(rs.getString("comment_contents"));
					comment.setUserSerial(rs.getInt("user_serial"));
					comment.setCommentDate(rs.getString("comment_date"));
					list.add(comment);
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return list;
		} // myBoardList
		public boolean myCommentDelete(int serial, int userSerial) {
			Connection conn = getConnect();
			String sql = "update tb_board_comment set comment_status = -1 where comment_serial = ? and user_serial = ?";

			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, serial);
				psmt.setInt(2, userSerial);
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
		
		public List<Comment> boardComment(int serial){
			List<Comment> list = new ArrayList<Comment>();
			Connection conn = getConnect();
			String sql = "select * from tb_board_comment where board_serial = ?";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, serial);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					Comment comment = new Comment();
					comment.setBoardSerial(rs.getInt("board_serial"));
					comment.setCommentSerial(rs.getInt("comment_serial"));
					comment.setUserSerial(rs.getInt("user_serial"));
					comment.setCommentContents(rs.getString("comment_contents"));
					comment.setCommentDate(rs.getString("comment_date"));
					list.add(comment);
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		public boolean boardCommentInsert(Comment comment) {
			Connection conn = getConnect();
			String sql = "insert into tb_board_comment(\r\n"
					+ "  comment_serial,\r\n"
					+ "  board_serial,\r\n"
					+ "  comment_contents,\r\n"
					+ "  user_serial,\r\n"
					+ "  comment_date,\r\n"
					+ "  comment_status)\r\n"
					+ "values(\r\n"
					+ "  tb_board_comment_sequence.nextval,\r\n"
					+ "  ?,\r\n"
					+ "  ?,\r\n"
					+ "  ?,\r\n"
					+ "  sysdate,\r\n"
					+ "  1)";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, comment.getBoardSerial());
				stmt.setString(2, comment.getCommentContents());
				stmt.setInt(3, comment.getUserSerial());
				
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
		
		public String commentUserName(int commentSerial) {
			Connection conn = getConnect();
			String sql = "select user_name from tb_member where\r\n"
					+ "user_serial = (select user_serial from tb_board_comment where comment_serial = ?)";
			String name = "";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, commentSerial);
				ResultSet rs = psmt.executeQuery();
				if (rs.next()) {
					conn.close();
					name = rs.getString(1);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return name;
		}
		
}
