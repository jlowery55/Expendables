<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="myStyle.css">
<title>userStoryThreeHandler</title>
</head>

<body> 
<% if (myUtil.getConn()==null) { %>
   <jsp:forward page="openForm.jsp"></jsp:forward>
<% } %>  

<%@ include file="headStudent.jsp"%>


<h2> The List of Courses an Admin can Tutor</h2>

<form action = "userStoryThreeHandlerStudent.jsp" method = "post"> 

Input an Admin's First Name and Last Name. <br>
<br>

<label for = "name"> First Name: </label>
<input type = "text" id = "fname" name = "fname" required> <br>

<label for = "name"> Last Name: </label>
<input type = "text" id = "lname" name = "lname" required> <br>

<input type = "submit" value = "Submit">


</form>


</body>
</html>
