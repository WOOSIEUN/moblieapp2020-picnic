import java.sql.*;
import java.text.*;

public class DBConnect {

	public static Connection getMySQLConnection(int mode) {
		Connection conn = null;
		
		//�̸� ������ �� AWS RDS DB �ν��Ͻ��� ����
		String driver = "com.mysql.cj.jdbc.Driver";
		String dbURL = "jdbc:mysql://moappdb.co0p7vaxsheo.us-east-2.rds.amazonaws.com/";
		String dbName = "";
		String user = "admin";
		String pass = ""; //commit�� �ش�κ� �׻� ������ �� 

		try {
			Class.forName(driver); 						
			conn = DriverManager.getConnection(dbURL+dbName, user, pass);
			System.out.println("Success");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}
}
