import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.sql.Date;
public class MySQL {
	public static final String URL = "jdbc:mysql://moappdb.co0p7vaxsheo.us-east-2.rds.amazonaws.com:3306/MoApp?serverTimezone=UTC";
	public static final String USER_UNIVERSITY ="admin";
	public static final String USER_PASSWD ="dlgud123";
	public static String TABLE_NAME = "";
	
	
    public static void insert(String table, String ccbaMnm1, String ccmaName, String ccbaLcad, String ccceName, String content, String ccbaAsdt, String ccbaCtcd, String imageUrl, String latitude, String longitude) {
		Connection conn = null; // Connection object
		TABLE_NAME = table;
		PreparedStatement pstmt;
		try {
			// Load a JDBC driver for Oracle DBMS
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Get a Connection object 
			System.out.println("Success!");
		}catch(ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		try {
			conn = DriverManager.getConnection(URL, USER_UNIVERSITY, USER_PASSWD);
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
		}

		String SQL = "INSERT INTO " + TABLE_NAME + "(ccbaMnm1, ccmaName, ccbaLcad, ccceName, content, ccbaAsdt, ccbaCtcd, imageUrl, latitude, longitude) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			if(ccbaMnm1.equals(""))
				pstmt.setNull(1, Types.VARCHAR);
			else
				pstmt.setString(1, ccbaMnm1);
			if(ccmaName.equals(""))
				pstmt.setNull(2, Types.VARCHAR);
			else
				pstmt.setString(2, ccmaName);
			if(ccbaLcad.equals(""))
				pstmt.setNull(3, Types.VARCHAR);
			else
				pstmt.setString(3, ccbaLcad);
			if(ccceName.equals(""))
				pstmt.setNull(4, Types.VARCHAR);
			else 
				pstmt.setString(4, ccceName);
			if(content.equals(""))
				pstmt.setNull(5, Types.VARCHAR);
			else
				pstmt.setString(5, content);
			if(ccbaAsdt.equals(""))
				pstmt.setNull(6, Types.DATE);
			else {
				java.util.Date d = new java.text.SimpleDateFormat("yyyyMMdd").parse(ccbaAsdt);
				java.sql.Date s = new java.sql.Date(d.getTime());
				pstmt.setDate(6, s);
			}
			if(ccbaCtcd.equals(""))
				pstmt.setNull(7, Types.INTEGER);
			else
				pstmt.setInt(7, Integer.parseInt(ccbaCtcd));
			if(imageUrl.equals(""))
				pstmt.setNull(8, Types.VARCHAR);
			else
				pstmt.setString(8, imageUrl);
			if(latitude.equals(""))
				pstmt.setNull(9, Types.DOUBLE);
			else
				pstmt.setDouble(9, Double.parseDouble(latitude));
			if(longitude.equals(""))
				pstmt.setNull(10, Types.DOUBLE);
			else
				pstmt.setDouble(10, Double.parseDouble(longitude));
			pstmt.executeUpdate();
			System.out.println("Insert success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
