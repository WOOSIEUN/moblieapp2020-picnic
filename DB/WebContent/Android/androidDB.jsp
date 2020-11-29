<%@ page import="com.db.DBConnect.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
	request.setCharacterEncoding("UTF-8");
	String DBTable = request.getParameter("DBTable");
	int mode = Integer.valueOf(request.getParameter("mode"));
	Double latitude = Double.valueOf(request.getParameter("latitude"));
	Double longitude = Double.valueOf(request.getParameter("longitude"));
	DBConnect connect = DBConnect.getInstance();
	String returnStr = connect.connectionDB(DBTable, mode, latitude, longitude);
	out.println(returnStr);
	System.out.println(returnStr);
%>