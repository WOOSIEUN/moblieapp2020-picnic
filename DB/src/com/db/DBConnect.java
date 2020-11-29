package com.db;

import java.sql.*;
import java.text.*;

public class DBConnect {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	Double dist = 0.05388;
	String returnString;
	
	//�̸� ������ �� AWS RDS DB �ν��Ͻ��� ����
	String driver = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:mysql://moappdb.co0p7vaxsheo.us-east-2.rds.amazonaws.com/";
	String dbName = "MoApp";
	String user = "admin";
	String pass = ""; //commit�� �ش�κ� �׻� ������ �� 
	
	
	private static DBConnect instance = new DBConnect();

	public static DBConnect getInstance() {
		return instance;
	}
	
	public String DBConnect_SQL(String DBTable, int mode, Double latitude, Double longitude) {
		
		try {
			//Connect
			Class.forName(driver); 						
			conn = DriverManager.getConnection(dbURL+dbName, user, pass);
			System.out.println("Connection Success");
			
			//SQL�� ����
			if (mode == 1) { //����� ��ġ��� �˻�
				sql = "SELECT id, latitude, longitude FROM" + DBTable + "WHERE latitude <= " + (latitude + dist) + " AND latitude >= " + (latitude - dist) + " AND longitude >= " + (longitude - dist) + " AND longitude <= " + (longitude + dist) + ";";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					returnString += rs.getString(1) + "#" + rs.getString(2) + "#" + rs.getString(3) + "#"; 
				}
				
			} else if (mode == 2) {
				//������ �ۼ�
				//�� ���̺� �°� ������ ��������
				if (DBTable.equals("FESTIVAL")) {
					while(rs.next()){
						
					}	
				} else if (DBTable.equals("TOUR")) {					
										
				} else if (DBTable.equals("HERITAGE")) {	
										
				}
			}
				//------------ �ٸ� ��� �߰��� ���⿡ �߰� �ʿ� ---------------
			
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {pstmt.close();}
				catch (SQLException ex) {}
			}
			if (conn != null) {
				try {conn.close();}
				catch (SQLException ex) {}
			}
		}
		return returnString;
	}		
}