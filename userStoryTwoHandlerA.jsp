<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<jsp:useBean id="myUtil"  class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Handler for User Story Two</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>

<%@ include file="head.jsp" %>
<h1>Handler For User Story Two</h1>
<%	   
   ResultSet rs = myUtil.studentsInterestedAdmin();
   if(!rs.first())
   { %>
	   There are no students interested in study groups
<% } else {
%>

<table>
<tr><th>Course Name</th> <th>Course ID</th> <th>First, Last</th></tr> 
<%while (rs.next()) { %>
 <tr> <td><%= rs.getString(1) %> </td> 
      <td><%= rs.getString(2) %> </td> 
      <td><%= rs.getString(3) + ", " + rs.getString(4) %> </td>
 </tr>
<%} %>
</table>
<% } %>




</body>
</html>
