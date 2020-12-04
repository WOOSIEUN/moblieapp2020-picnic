<%@ page import="com.db.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String DBTable = request.getParameter("DBTable");
	String mode = request.getParameter("mode");
	String latitude = request.getParameter("latitude");
	String longitude = request.getParameter("longitude");
	String id = request.getParameter("id");
	String location = request.getParameter("location");
	String returnStr = "";
	if(mode.equals("1")){
		DBConnect connect = DBConnect.getInstance();
		returnStr = connect.DBConnect_SQL(DBTable, mode, latitude, longitude);
	}
	else if(mode.equals("2")){
		DBConnectLocation connect = DBConnectLocation.getInstance();
		returnStr = connect.DBConnect_SQL(DBTable, mode, location);
	}
	else if(mode.equals("3")){
		DBConnectInfo connect = DBConnectInfo.getInstance();
		returnStr = connect.DBConnect_SQL(DBTable, mode, id);
		
	}
	out.println(returnStr);
	System.out.println(returnStr);
%>