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

<%@ include file="headStudent.jsp" %>

<h1> Courses the Admin can Tutor </h1>

<%
String first1 = request.getParameter("fname");
String last1 = request.getParameter("lname");
ResultSet rs = myUtil.adminTutor(first1, last1);
%>

<table>

<tr><th> Admin's Name</th> <th> Course Name </th>  <th> CourseID </th>
<% while(rs.next()) { %>
	<tr><td> <%= rs.getString(1) +  " " + rs.getString(2) %></td>
	<td> <%= rs.getString(3)%></td>
	<td> <%= rs.getString(4)%></td>
	</tr>


<%} %>
	
<% if(myUtil.isAdmin(first1,last1) != true) { 
    out.println("The Admin Inputted is not in the Database.");
 } %>

</table>


</body>
</html>
