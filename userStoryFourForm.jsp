<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Leave a Study Group</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>


<%@ include file="head.jsp" %>

<form action = "loginForm.jsp" method = "get">

<% String student = request.getParameter("ID"); 
%> 

</form>
<%ResultSet rs = myUtil.getStudyGroups(student); %>

<h1>Leave a Study Group (4) </h1>
<form action="UserStory4Handler.jsp" method="get">

<p></p>
Student ID: <%=student %>
<input type="hidden" name="studentID2" value="<%=student%>">

<p></p>

Select a group you  wish to leave. 
<table>
<tr><th>Check</th><th>Study Group ID</th><th>Course</th><th>Meeting Time</th><th>Meeting Day</th><th>Location</th><th>Administrator</th>

<%while(rs.next()){
	String admin = rs.getString(6) + " " + rs.getString(7);
	String sgID = rs.getString(1);
	String course = rs.getString(2);
	String time = rs.getString(3);
	String day = rs.getString(4);
	String location = rs.getString(5);%>
	
	<tr>
	<td> <input type="checkbox" name = "delete" value = <%=sgID%>></td>
	
	<td><%=sgID %></td>
	<td><%=course %></td>
	<td><%=time %></td>
	<td><%=day %></td>
	<td><%=location %></td>
	<td><%=admin %></td>
	</tr>
<%} %>
</table>
<p>
   <input type="submit" value="Delete">
</form>

</body>
</html>
