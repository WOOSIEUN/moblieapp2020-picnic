package com.db;

import java.sql.*;

public class DBConnectLocation {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	Double dist = 0.05388;
	String returnString;

	// �̸� ������ �� AWS RDS DB �ν��Ͻ��� ����
	String driver = "com.mysql.cj.jdbc.Driver";
	String dbURL = "jdbc:mysql://moappdb.co0p7vaxsheo.us-east-2.rds.amazonaws.com:3306/";
	String dbName = "MoApp";
	String user = "admin";
	String pass = "dlgud123"; // commit�� �ش�κ� �׻� ������ ��

	private static DBConnectLocation instance = new DBConnectLocation();

	public static DBConnectLocation getInstance() {
		return instance;
	}

	public DBConnectLocation() {

	}

	public String DBConnect_SQL(String DBTable, String modeStr, String location) {
		if (DBTable == null || modeStr == null || location == null) {
			return null;
		}
		try {
			// Connect
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL + dbName, user, pass);
			System.out.println("Connection Success");

			// SQL�� ����

			if (DBTable.equals("FESTIVAL")) {
				sql = "SELECT id, fstvlNm FROM " + DBTable + " WHERE rdnmadr LIKE '%" + location + "%';";
			} else if (DBTable.equals("TOUR")) {
				sql = "SELECT id, trrsrtNm FROM " + DBTable + " WHERE rdnmadr LIKE '%" + location + "%';";
			} else if (DBTable.equals("HERITAGE")) {
				sql = "SELECT id, ccbaMnm1 FROM " + DBTable + " WHERE ccbaCtcd = " + location + ";";
			}
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			returnString = "";
			while (rs.next()) {
				returnString += rs.getString(1) + "#" + rs.getString(2) + "#";
			}

			// ------------ �ٸ� ��� �߰��� ���⿡ �߰� �ʿ� ---------------

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
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
				}
			}
		}
		return returnString;
	}
}