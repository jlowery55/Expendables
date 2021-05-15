<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Close DB Connection</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>

<%@ include file="head.jsp" %>
<h1>Close connection to the Database</h1>
<% out.println("value of conn before closing: " + myUtil.getConn() + "<br>");
   if (myUtil.getConn() == null){
	   out.println("The Database connection was closed or timed out");
   }
   else{
      myUtil.closeDB();
      out.println("value of conn after closing: " + myUtil.getConn() + "<br>");
   }
%>

</body>
</html>