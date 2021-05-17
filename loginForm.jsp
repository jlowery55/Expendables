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
<%@ include file="headLogin.jsp" %>
<%String id = request.getParameter("ID"); %>

<% if (myUtil.getConn()==null) { %>
   <jsp:forward page="openForm.jsp"></jsp:forward>
<% } %>

<form action = "loginFormHandler.jsp" method ="get">
<h1>Login Page</h1>

<p>Please enter your ID: </p>
<input type="text" name="ID" value="<%=id %>" size="15" required><br><br>

<p>Please enter your password: </p>
<input type="password" name="pass" value="" size="30" required><br><br>


<input type="submit" name="submit" value="Login" ><br><br>



</form>


</body>
</html>
