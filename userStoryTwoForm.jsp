<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Finds Students Interested In Joining Study Groups</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>

<% if (myUtil.getConn()==null) { %>
   <jsp:forward page="openForm.jsp"></jsp:forward>
<% } %>  

<%@ include file="headAdmin.jsp" %>

<h1>Finds Students Interested In Joining Study Groups</h1>
<form action= "userStoryTwoHandlerA.jsp" method ="post">

<%
String student = (String)session.getAttribute("SES_ID");%>

Please submit the query. 

<br>
<input type="submit" value = "Submit Query">
</form>

</body>
</html>
