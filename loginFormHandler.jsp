<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<jsp:useBean id="myUtil"  class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Handler Title</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>

<%@ include file="head.jsp" %>
<h1>Handler For Login</h1>
<%	String user = request.getParameter("ID"); 
	session.setAttribute("SES_ID", user); 


if(myUtil.isAdmin(user)){%>
You are an administrator.
<jsp:forward page= "indexAdmin.jsp"></jsp:forward>
<%} %>
<%if(myUtil.isStudent(user)){ %>
You are a student. 
<jsp:forward page= "indexStudent.jsp"></jsp:forward>

<%} %>

<input type="submit" value = "continue">



</body>
</html>
