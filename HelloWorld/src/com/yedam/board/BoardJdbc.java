package com.yedam.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BoardJdbc {
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

	public List<Board> myBoardList(int serial) {
		List<Board> list = new ArrayList<Board>();
		Connection conn = getConnect();
		String sql = "select * from tb_board where user_serial = ? and board_status = 1 order by board_serial";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, serial);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBoardSerial(rs.getInt("board_serial"));
				board.setUserSerial(rs.getInt("user_serial"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContents(rs.getString("board_contents"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardUpDate(rs.getString("board_up_date"));
				list.add(board);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	} // myBoardList

	public List<Board> myLikeBoard(int serial) {
		List<Board> list = new ArrayList<Board>();
		Connection conn = getConnect();
		String sql = "select\r\n"
				+ "  b.board_serial,\r\n"
				+ "  b.user_serial,\r\n"
				+ "  b.board_title, \r\n"
				+ "  b.board_contents,\r\n"
				+ "  b.board_date\r\n"
				+ "from \r\n"
				+ "  tb_board b join tb_board_like l\r\n"
				+ "  on (b.board_serial = l.board_serial)\r\n"
				+ "where\r\n"
				+ "  l.user_serial = ? and b.board_status = 1";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, serial);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBoardSerial(rs.getInt("board_serial"));
				board.setUserSerial(rs.getInt("user_serial"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContents(rs.getString("board_contents"));
				board.setBoardDate(rs.getString("board_date"));
				list.add(board);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	} // myLikeBoard
	
	
	public Board myBoardShow(int serial, int userSerial) {
		Connection conn = getConnect();
		String sql = "select * from tb_board where board_serial = ? and user_serial = ? and board_status = 1";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, serial);
			psmt.setInt(2, userSerial);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				Board board = new Board();
				board.setBoardSerial(rs.getInt("board_serial"));
				board.setUserSerial(rs.getInt("user_serial"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContents(rs.getString("board_contents"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardUpDate(rs.getString("board_up_date"));

				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public boolean myBoardContentDelete(int serial, int userSerial) {
		Connection conn = getConnect();
		String sql = "update tb_board set board_status = -1 where board_serial = ? and user_serial = ?";

		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, serial);
			psmt.setInt(2, userSerial);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean myBoardContentUpdate(Board board) {
		Connection conn = getConnect();
		String sql = "update tb_board set board_title = ?, board_contents = ?, board_up_date = sysdate where board_serial = ? and user_serial = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContents());
			psmt.setInt(3, board.getBoardSerial());
			psmt.setInt(4, board.getUserSerial());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true; // 수정성공
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Board> tbBoard(){
		List<Board> list = new ArrayList<Board>();
		Connection conn = getConnect();
		String sql = "select * from tb_board where board_status = 1 order by board_serial";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBoardSerial(rs.getInt("board_serial"));
				board.setUserSerial(rs.getInt("user_serial"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContents(rs.getString("board_contents"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardUpDate(rs.getString("board_up_date"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Board boardContent(int serial) {
		Connection conn = getConnect();
		String sql = "select * from tb_board where board_serial = ? and board_status = 1";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, serial);

			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				Board board = new Board();
				board.setBoardSerial(rs.getInt("board_serial"));
				board.setUserSerial(rs.getInt("user_serial"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContents(rs.getString("board_contents"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardUpDate(rs.getString("board_up_date"));

				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean boardLike(int boardSerial, int userSerial) {
		Connection conn = getConnect();
		String sql = "MERGE INTO tb_board_like USING dual\r\n"
				+ "ON (board_serial = ? AND user_serial = ?)\r\n"
				+ "WHEN NOT MATCHED THEN\r\n"
				+ "    INSERT (board_serial, user_serial, board_like_date)\r\n"
				+ "    VALUES (?, ?, SYSDATE)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardSerial);
			psmt.setInt(2, userSerial);
			psmt.setInt(3, boardSerial);
			psmt.setInt(4, userSerial);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean boardInsert(Board board) {
		Connection conn = getConnect();
		String sql = "insert into tb_board(\r\n"
				+ "  board_serial,\r\n"
				+ "  user_serial,\r\n"
				+ "  board_title,\r\n"
				+ "  board_contents,\r\n"
				+ "  board_date,\r\n"
				+ "  board_up_date,\r\n"
				+ "  board_status)\r\n"
				+ "values(\r\n"
				+ "  tb_board_sequence.nextval,\r\n"
				+ "  ?,\r\n"
				+ "  ?,\r\n"
				+ "  ?,\r\n"
				+ "  sysdate,\r\n"
				+ "  null,\r\n"
				+ "  1)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, board.getUserSerial());
			stmt.setString(2, board.getBoardTitle());
			stmt.setString(3, board.getBoardContents());
			
			int r = stmt.executeUpdate();
			if (r > 0) {
				return true; // 등록성공
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int boardLikeCount(int boardSerial) {
		Connection conn = getConnect();
		String sql = "select count(*) from tb_board_like where board_serial = ?";
		int count = 0;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardSerial);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
}
