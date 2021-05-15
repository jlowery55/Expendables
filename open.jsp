<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>

<%@ include file="head.jsp" %>

<h1>Form Data From openForm.html</h1>
<p>
	The user is:<%= request.getParameter("user") %> <br>
	The  password is:<%="*******" %> <br>	
	The current value of connection is:<%= myUtil.getConn() %><br>
	
	<% String user = request.getParameter("user");
	   String password = request.getParameter("pw");
	   String location = request.getParameter("location");
	   myUtil.openDB2(user, password, location);
	%>
</p>
	<p>
	After call to open(user,password,location) <br>
	The database connection is:<%= myUtil.getConn() %>
	</p>

</body>
</html>