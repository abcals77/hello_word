package com.yedam.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.yedam.bookApp.Book;

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
				Board Board = new Board();
				Board.setBoardSerial(rs.getInt("board_serial"));
				Board.setUserSerial(rs.getInt("user_serial"));
				Board.setBoardTitle(rs.getString("board_title"));
				Board.setBoardContents(rs.getString("board_contents"));
				Board.setBoardDate(rs.getString("board_date"));
				Board.setBoardUpDate(rs.getString("board_up_date"));
				list.add(Board);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	} // myBoardList

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
}
