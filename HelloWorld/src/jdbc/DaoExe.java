package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 1. ojdbc 데이터베이스 연겨, 쿼리, 실행결과.
 * 2. Connection 객체(db session)
 * 3. Statement(PreparedStatement) 쿼리 실행
 * 4. ResultSet(조회), int(입력, 수정, 삭제)
 */
public class DaoExe {
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userId = "scott";
		String userPw = "tiger";
		String sql = "select empno, ename, job,mgr, hiredate, sal\r\n"
				+ "from emp order by empno desc";
		try {
			Connection conn = DriverManager.getConnection(url, userId, userPw);
			System.out.println("연결성공");
			conn.setAutoCommit(false); // autoCommit 사용여부
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate("delete from emp\r\n"
					+ "where empno = 9999");
			if(r>0) {
				System.out.println("삭제성공.");
				conn.commit(); // 커밋처리.
			} // 자동 commit
			
			ResultSet rs = stmt.executeQuery(sql); // 쿼리작성. -> 실행.
			while(rs.next()) { // next() : 조회된 데이터를 행을 기준으로 반환, 더 이상 행이 없을 경우 false를 반환하고 while 종료
				System.out.println(rs.getInt("empno") + "," + rs.getString("ename") + "," + rs.getString("job")); // getInt 해당하는 컬럼을 int값으로 반환
			}
			System.out.println("-- end of data --");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
