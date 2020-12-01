package com.db;

import java.sql.*;

public class DBConnect {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	Double dist = 0.05388;
	String returnString;
	
	//�̸� ������ �� AWS RDS DB �ν��Ͻ��� ����
	String driver = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:mysql://moappdb.co0p7vaxsheo.us-east-2.rds.amazonaws.com:3306/";
	String dbName = "MoApp";
	String user = "admin";
	String pass = "dlgud123"; //commit�� �ش�κ� �׻� ������ �� 
	
	
	private static DBConnect instance = new DBConnect();

	public static DBConnect getInstance() {
		return instance;
	}
	
	public DBConnect() {

	}
	
	public String DBConnect_SQL(String DBTable, String modeStr, String latitudeStr, String longitudeStr, String idStr) {
		if(DBTable == null || modeStr == null || latitudeStr == null || longitudeStr == null || idStr == null) {
			return null;
		}
		try {
			int mode = Integer.parseInt(modeStr);
			Double latitude = Double.valueOf(latitudeStr);
			Double longitude = Double.valueOf(longitudeStr);
			int id = Integer.parseInt(idStr);
			//Connect
			Class.forName("com.mysql.jdbc.Driver"); 						
			conn = DriverManager.getConnection(dbURL+dbName, user, pass);
			System.out.println("Connection Success");
			
			//SQL�� ����
			if (mode == 1) { //����� ��ġ��� �˻�
				//�� ���̺� �°� ������ ����
				if (DBTable.equals("FESTIVAL")) {
					sql = "SELECT id, fstvlNm, latitude, longitude FROM " + DBTable + " WHERE latitude <= " + (double)(latitude + dist) + " AND latitude >= " + (double)(latitude - dist) + " AND longitude >= " + (double)(longitude - dist) + " AND longitude <= " + (double)(longitude + dist) + ";";
				} else if (DBTable.equals("TOUR")) {					
					sql = "SELECT id, trrsrtNm, latitude, longitude FROM " + DBTable + " WHERE latitude <= " + (double)(latitude + dist) + " AND latitude >= " + (double)(latitude - dist) + " AND longitude >= " + (double)(longitude - dist) + " AND longitude <= " + (double)(longitude + dist) + ";";					
				} else if (DBTable.equals("HERITAGE")) {	
					sql = "SELECT id, ccbaMnm1, latitude, longitude FROM " + DBTable + " WHERE latitude <= " + (double)(latitude + dist) + " AND latitude >= " + (double)(latitude - dist) + " AND longitude >= " + (double)(longitude - dist) + " AND longitude <= " + (double)(longitude + dist) + ";";			
				}				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				returnString = null;
				while (rs.next()) {
					returnString += rs.getString(1) + "#" + rs.getString(2) + "#" + rs.getString(3) + "#" + rs.getString(4) + "#"; 
				}
				
			} else if (mode == 2) {
				sql = "SELECT id, fstvlNm, latitude, longitude FROM " + DBTable + " WHERE id = "+ id + ";";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				returnString = null;
				//�� ���̺� �°� ������ ��������
				if (DBTable.equals("FESTIVAL")) {
					while(rs.next()){
						returnString += rs.getString(1) + "#" + rs.getString(2) + "#" + rs.getString(3) + "#";
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
		} catch (NumberFormatException e) {
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