<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="myUtil" class="dbUtil.Utilities" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="myStyle.css">
<title>Form for User Story 1</title>
</head>

<body> 
<% if (myUtil.getConn()==null) { %>
   <jsp:forward page="openForm.jsp"></jsp:forward>
<% } %>  

<%@ include file="headAdmin.jsp" %>

<h1>Update a study group's meeting time</h1>
<%String errorcheck = (String)session.getAttribute("SES_CHECK"); %>
<%if(errorcheck!=null){
	out.println(errorcheck);
}%>
<form action="userStoryOneHandler.jsp" method="post">
<br>Please Input the new meeting time, Course ID, and study group ID<br>
	Meeting Time in the format: "HH:MM:SS" <br>
	<input type="text" id="meetingTime" name ="meetingTime"><br>
	Course ID: <br>
	<input type="text" id="courseID" name = "courseID"><br>
	Study Group ID: <br>
	<input type="text" id="studyGroupID" name = "studyGroupID"><br>
	
	<input type="submit" value="Submit new time">
</form>


</body>
</html>
