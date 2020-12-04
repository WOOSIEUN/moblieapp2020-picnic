package com.db;

import java.sql.*;

public class DBConnectInfo {
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

	private static DBConnectInfo instance = new DBConnectInfo();

	public static DBConnectInfo getInstance() {
		return instance;
	}

	public DBConnectInfo() {

	}

	public String DBConnect_SQL(String DBTable, String modeStr, String idStr) {
		if (DBTable == null || modeStr == null || idStr == null) {
			return null;
		}
		try {
			int id = Integer.parseInt(idStr);
			// Connect
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL + dbName, user, pass);
			System.out.println("Connection Success");

			// SQL�� ����

			// �� ���̺� �°� ������ ����
			if (DBTable.equals("FESTIVAL")) {
				sql = "SELECT fstvlNm, opar, fstvlStartDate, fstvlEndDate, fstvlCo, rdnmadr FROM " + DBTable
						+ " WHERE id = " + id + ";";
			} else if (DBTable.equals("TOUR")) {
				sql = "SELECT trrsrtNm, trrsrtIntrcn, institutionNm, rdnmadr FROM " + DBTable + " WHERE id = " + id
						+ ";";
			} else if (DBTable.equals("HERITAGE")) {
				sql = "SELECT ccbaMnm1, ccmaName, ccbaLcad, ccceName, content, imageUrl FROM " + DBTable
						+ " WHERE id = " + id + ";";
			}
			System.out.println(sql);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			returnString = "";
			while (rs.next()) {
				if (DBTable.equals("FESTIVAL") || DBTable.equals("HERITAGE"))
					returnString += rs.getString(1) + "#" + rs.getString(2) + "#" + rs.getString(3) + "#"
							+ rs.getString(4) + "#" + rs.getString(5) + "#" + rs.getString(6) + "#";
				else
					returnString += rs.getString(1) + "#" + rs.getString(2) + "#" + rs.getString(3) + "#"
							+ rs.getString(4) + "#";

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