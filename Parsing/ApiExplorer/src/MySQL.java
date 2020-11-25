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
	
	
    private Connection conn;
    
    public static void insert(String table, String fstvlNm, String opar, String fstvlStartDate, String fstvlEndDate, String fstvlCo, String mnnst, String rdnmadr, String lnmadr, String latitude, String longitude) {
		Connection conn = null; // Connection object
		Statement stmt = null;	// Statement object
		String sql = ""; // an SQL statement 
		TABLE_NAME = table;
		PreparedStatement pstmt;  
		ResultSet rs;   
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

		String SQL = "INSERT INTO " + TABLE_NAME + "(fstvlNm, opar, fstvlStartDate, fstvlEndDate, fstvlCo, mnnst, rdnmadr, lnmadr, latitude, longitude) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			if(fstvlNm.equals(""))
				pstmt.setNull(1, Types.VARCHAR);
			else
				pstmt.setString(1, fstvlNm);
			if(opar.equals(""))
				pstmt.setNull(2, Types.VARCHAR);
			else
				pstmt.setString(2, opar);
			if(fstvlStartDate.equals(""))
				pstmt.setNull(3, Types.DATE);
			else {
				Date date = Date.valueOf(fstvlStartDate);
				pstmt.setDate(3, date);
			}
			if(fstvlEndDate.equals(""))
				pstmt.setNull(4, Types.DATE);
			else {
				Date date = Date.valueOf(fstvlEndDate);
				pstmt.setDate(4, date);
			}
			if(fstvlCo.equals(""))
				pstmt.setNull(5, Types.VARCHAR);
			else
				pstmt.setString(5, fstvlCo);
			if(mnnst.equals(""))
				pstmt.setNull(6, Types.VARCHAR);
			else
				pstmt.setString(6, mnnst);
			if(rdnmadr.equals(""))
				pstmt.setNull(7, Types.VARCHAR);
			else
				pstmt.setString(7, rdnmadr);
			if(lnmadr.equals(""))
				pstmt.setNull(8, Types.VARCHAR);
			else
				pstmt.setString(8, lnmadr);
			if(latitude.equals(""))
				pstmt.setNull(9, Types.DOUBLE);
			else
				pstmt.setDouble(9, Double.parseDouble(latitude));
			if(longitude.equals(""))
				pstmt.setNull(10, Types.DOUBLE);
			else
				pstmt.setDouble(10, Double.parseDouble(latitude));
			pstmt.executeUpdate();
			System.out.println("Insert success!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
