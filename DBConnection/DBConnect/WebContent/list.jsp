<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<%@ page import="DBConnect.*" %>
<% 
   request.setCharacterEncoding("UTF-8");
   
   String returns = "";
   String ccbaCtcd = request.getParameter("ccbaCtcd");
   

%>
<%
	//�̱��� ������� �ڹ� Ŭ������ �ҷ��ɴϴ�.
	DBConnect connect = DBConnect.getInstance();
	returns = connect.connectionDB(ccbaCtcd);
	out.println(returns);
	System.out.println("��û ��� : " + returns);
%>