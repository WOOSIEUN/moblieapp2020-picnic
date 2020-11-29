package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {
	// 싱글톤 패턴으로 사용 하기위 한 코드들
	private static DBConnect instance = new DBConnect();

	public static DBConnect getInstance() {
		return instance;
	}

	public DBConnect() {

	}

	String jdbcUrl = "jdbc:mysql://moappdb.co0p7vaxsheo.us-east-2.rds.amazonaws.com:3306/MoApp"; // MySQL 계정
	String dbId = "admin"; // MySQL 계정
	String dbPw = "dlgud123"; // 비밀번호
	Connection conn = null;
	PreparedStatement pstmt = null;
	PreparedStatement pstmt2 = null;
	ResultSet rs = null;
	String sql = "";
	String returns = "";

	// 데이터베이스와 통신하기 위한 코드가 들어있는 메서드
	public String connectionDB(String ccbaCtcd) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// 디비 연결
			conn = DriverManager.getConnection("jdbc:mysql://moappdb.co0p7vaxsheo.us-east-2.rds.amazonaws.com:3306/MoApp", "admin", "dlgud123");
			sql = "SELECT ccbaMnm1, ccmaName, ccbaLcad, ccceName, content, imageUrl FROM HERITAGE WHERE ccbaCtcd = ?";// 조회
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ccbaCtcd);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				returns += rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t"; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt2 != null)try {pstmt2.close();	} catch (SQLException ex) {}
			if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
			if (conn != null)try {conn.close();	} catch (SQLException ex) {	}
		}
		return returns;
	}

}