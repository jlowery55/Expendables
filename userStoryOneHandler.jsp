<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="myStyle.css">
<title>Handler for User Story 1</title>
</head>

<body> 
<% if (myUtil.getConn()==null) { %>
   <jsp:forward page="openForm.jsp"></jsp:forward>
<% } %>  

<%@ include file="head.jsp" %>
<h1>Handler For User Story 1</h1>
<% String meetingTime = request.getParameter("meetingTime");
   String courseID = request.getParameter("courseID");
   String sgID = request.getParameter("studyGroupID");
   		int sgID2 = Integer.parseInt(sgID);
   int result = myUtil.updateStudyGroupTime(meetingTime,courseID,sgID2);
   out.println("Number of updated meeting times: "+ result);
   ResultSet rset=myUtil.displaySGInfo(sgID2);
%>

<table>
<tr><th>Study Group ID</th> <th>Course ID</th> <th>Meeting Time</th> <th>Meeting Days</th> <th>Location</th> <th>AdminID</th> </tr>
<%while (rset.next()) { %>
 <tr> <td><%= rset.getString(1) %> </td> 
 	  <td><%= rset.getString(2) %> </td> 
 	  <td><%= rset.getString(3) %> </td> 
      <td><%= rset.getString(4) %> </td> 
      <td><%= rset.getString(5) %> </td>
      <td><%= rset.getString(6) %> </td> 
 </tr>
<%} %>
</table>

</body>
</html>