<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<jsp:useBean id="myUtil"  class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Handler Title</title>
<link rel="stylesheet" href="myStyle.css">
</head>
<body>

<%@ include file="headStudent.jsp" %>
<h1>Handler For JoinStudyGroup</h1>
<%	String studentID = (String)session.getAttribute("SES_ID");
	String sgID = request.getParameter("sgID");
	int inserted = myUtil.joinStudyGroup(studentID, Integer.parseInt(sgID));
	String output;
	if(inserted == 1)
		output = "Study group successfully joined";
	else
		output = "Study group could not be joined, please try again";
%>

<p><%=output %> <br></p>
<%ResultSet rs = myUtil.getStudyGroups(studentID); %>
<table>
<tr><th>Study Group ID</th> <th>Course</th> <th>Meeting Time</th> <th>Meeting Day</th> <th>Location</th> <th>Administrator</th></tr>
<%while (rs.next()) { %>
 <tr> <td><%= rs.getString(1) %> </td> 
 	  <td><%= rs.getString(2) %> </td> 
      <td><%= rs.getString(3) %> </td> 
      <td><%= rs.getString(4) %> </td>
      <td><%= rs.getString(5) %> </td>
      <td><%= rs.getString(6) + " " + rs.getString(7) %></td>
 </tr>
<%} %>
</table> <br>

</body>
</html>
