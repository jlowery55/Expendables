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

<%@ include file="head.jsp" %>
<h1>Handler For ShowInterest</h1>
<%	String studentID = request.getParameter("studentID");
	String courseID = request.getParameter("courseID");
	boolean boo = myUtil.getStudentID(studentID);
	
	if(!boo) { %>
		<jsp:forward page = "userStoryFiveForm.jsp" >
		<jsp:param name = "invalid" value = "Invalid Student ID, please try again" />
		</jsp:forward>
  <%}
	
	int inserted = myUtil.showInterest(studentID, courseID);
	String output;
	if(inserted == 1)
		output = "Interested_in successfully updated";
	else
		output = "Interested_in could not be updated, please try again";
%>

<p><%=output %> <br></p>
<%ResultSet rs = myUtil.studentsInterested(); %>
<table>
<tr><th>Study Group</th> <th>Course ID</th> <th>Students Interested</th></tr>
<%while (rs.next()) { %>
 <tr> <td><%= rs.getString(1) %> </td> 
 	  <td><%= rs.getString(2) %> </td>
      <td><%= rs.getString(3) %> </td> 
 </tr>
<%} %>
</table> <br>

</body>
</html>
