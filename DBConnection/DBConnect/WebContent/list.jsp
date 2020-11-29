<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="DBConnect.*" %>
<% 
   request.setCharacterEncoding("UTF-8");
   
   String returns = "";
   String ccbaCtcd = request.getParameter("ccbaCtcd");
   

%>
<%
	//싱글톤 방식으로 자바 클래스를 불러옵니다.
	DBConnect connect = DBConnect.getInstance();
	returns = connect.connectionDB(ccbaCtcd);
	out.println(returns);
	System.out.println("요청 결과 : " + returns);
%>