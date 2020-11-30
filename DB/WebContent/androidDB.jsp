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
	DBConnect connect = DBConnect.getInstance();
	String returnStr = connect.DBConnect_SQL(DBTable, mode, latitude, longitude, id);
	out.println(returnStr);
	System.out.println(returnStr);
%>