package com.yedam.bookApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * ojdbc 를 활용.
 */
public class BookJdbc {

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
	
	// 추가.
	public boolean insert(Book book) {
		Connection conn = getConnect();
		String sql = "insert into tbl_book (\r\n"
				+ "  book_code,\r\n"
				+ "  book_title,\r\n"
				+ "  author,\r\n"
				+ "  company,\r\n"
				+ "  price)\r\n"
				+ "values(\r\n"
				+ "  book_seq.nextval, ?, ?, ?, ?)";
		try {
//			Statement stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1,book.getTitle());
			stmt.setString(2,book.getAuthor());
			stmt.setString(3,book.getCompany());
			stmt.setInt(4,book.getPrice());
			int r = stmt.executeUpdate();
			if (r > 0) {
				return true; // 등록성공
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 수정.
	public boolean update(Book book) {
		Connection conn = getConnect();
		String sql = "update tbl_book\r\n"
				+ "set\r\n"
				+ "  book_title = nvl(?, book_title),\r\n"
				+ "  price = ?,\r\n"
				+ "  author = nvl(?, author),\r\n"
				+ "  company = nvl(?, company)\r\n"
				+ "where \r\n"
				+ "  book_code = ?";
		try {
//			Statement stmt = conn.createStatement();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getTitle());
			stmt.setInt(2, book.getPrice());
			stmt.setString(3, book.getAuthor());
			stmt.setString(4, book.getCompany());
			stmt.setString(5, book.getBookCode());
			
			int r = stmt.executeUpdate();
			if (r > 0) {
				return true; // 수정성공
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 삭제.
	public boolean delete(String bookCode) {
		Connection conn = getConnect();
		String sql = "delete from tbl_book where book_code = " + bookCode;
		try {
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);
			if (r > 0) {
				return true; // 삭제성공
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 목록.
	public List<Book> list(String company) {
		List<Book> list = new ArrayList<Book>();
		Connection conn = getConnect();
		String sql = "select * from tbl_book where company = nvl(?,company) order by book_code";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, company);
			ResultSet rs = psmt.executeQuery(); // 데이터 정보를 rs 객체에 담음.
			while(rs.next()) { 
				Book book = new Book();
				book.setAuthor(rs.getString("author")); // rs 객체에 담긴 저자 정보를 book 객체에 다음
				book.setBookCode(rs.getString("book_code"));
				book.setCompany(rs.getString("company"));
				book.setPrice(rs.getInt("price"));
				book.setTitle(rs.getString("book_title"));
				list.add(book); // book 객체 정보를 컬렉션 list에 담음
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	} // end of 목록
	// 한 건 조회
	public Book select(String bcode) {
		Connection conn = getConnect();
		String sql = "select * from tbl_book where book_code = ?";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, bcode);
			
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) { // 조회된 결과가 있으면 
				Book book = new Book();
				book.setAuthor(rs.getString("author"));
				book.setBookCode(rs.getString("book_code"));
				book.setCompany(rs.getString("company"));
				book.setPrice(rs.getInt("price"));
				book.setTitle(rs.getString("book_title"));
				return book;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; // 조회결과 없음
	}
	
} // end of 클래스
