<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Study Help</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>
<%@ include file="head.jsp" %>
<%String id = request.getParameter("ID"); %>
<h1>Login Page</h1>
<form action="UserStory4Form.jsp" method="get">

<p>Please enter your ID: </p>
<input type="text" name="ID" value="<%=id %>" size="15" ><br><br>

<p>Please enter your password: </p>
<input type="password" name="pass" value="" size="30" ><br><br>

<%

//boolean inSystem = false;
//if(myUtil.isAdmin(id)){
	//navigate to only admin accessible 
//	inSystem = true;
//	%>
<%//} if(myUtil.isStudent(id)){
		//navigate to only student accessible
//		inSystem = true;
%>
<%//} else 
	//	inSystem = false;
		%>

<input type="submit" name="submit" value="Login" ><br><br>

</form>
</body>
</html>
