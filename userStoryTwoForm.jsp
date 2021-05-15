<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form For User Story Two</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>

<% if (myUtil.getConn()==null) { %>
   <jsp:forward page="openForm.jsp"></jsp:forward>
<% } %>  

<%@ include file="head.jsp" %>

<h1>Finds Students Interested In Joining Study Groups</h1>
<% String user  = request.getParameter("ID"); 
   boolean userStatus = myUtil.getAdminID(user);
   if(userStatus)
   { %>
	   <form action="userStoryTwoHandlerA.jsp" method="get">
<% }
   else
   { %>
	   <form action="userStoryTwoHandlerB.jsp" method="get">
 <%  } %>

<br>
<input type="submit">
</form>

</body>
</html>
